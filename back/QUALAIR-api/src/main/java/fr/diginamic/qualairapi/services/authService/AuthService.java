package fr.diginamic.qualairapi.services.authService;

// Importations de classes nécessaires

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.diginamic.qualairapi.dtos.auth.AuthenticationResponse;
import fr.diginamic.qualairapi.dtos.auth.LoginRequest;
import fr.diginamic.qualairapi.dtos.auth.RefreshTokenRequest;
import fr.diginamic.qualairapi.dtos.auth.UserCreationDto;
import fr.diginamic.qualairapi.entities.Role;
import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.exceptions.DuplicateEntryException;
import fr.diginamic.qualairapi.jwt.JwtProvider;
import fr.diginamic.qualairapi.repositories.UserRepository;
import fr.diginamic.qualairapi.repositories.authRepository.VerificationTokenRepository;
import fr.diginamic.qualairapi.services.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService {

    // Déclaration des dépendances
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserMapper userMapper;

    // Méthode pour l'inscription d'un utilisateur
    public void signup(UserCreationDto userCreationDto) {
        try {  // Création d'un objet utilisateur
            User user = new User();
            user.setFirstName(userCreationDto.getFirstName());
            user.setLastName(userCreationDto.getLastName());
            user.setEmail(userCreationDto.getEmail());
            user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
            user.setRole(Role.USER);

            // Enregistrement de l'utilisateur dans la base de données

            userRepository.save(user);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateEntryException(" L'émail existe déjà  ");
        }

    }

    // Méthode pour gérer la connexion d'un utilisateur
    public AuthenticationResponse login(LoginRequest loginRequest) throws JsonProcessingException {
        // Authentification de l'utilisateur
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // Génération d'un jeton JWT
        String token = jwtProvider.generateToken(authenticate);

        // Création d'une réponse d'authentification
        AuthenticationResponse response =  AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .email(loginRequest.getEmail())
                .build();
        return response;
    }

    // Méthode pour rafraîchir le jeton JWT
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws Exception {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getEmail());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .email(refreshTokenRequest.getEmail())
                .build();
    }

}
