package fr.diginamic.qualairapi.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.qualairapi.dtos.UserDto;
import fr.diginamic.qualairapi.repositories.UserRepository;
import fr.diginamic.qualairapi.services.UserService;
import fr.diginamic.qualairapi.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class JwtProvider implements AuthenticationProvider {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final UserService userService;
    private  final UserMapper userMapper;
    private  final ObjectMapper objectMapper;

    // Récupère la durée de validité du token JWT depuis la configuration de l'application
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    // Génère un token JWT à partir de l'objet Authentication fourni
    public String generateToken(Authentication authentication) throws JsonProcessingException {
        System.out.println(authentication + "=======================================================////");
        // Extrait le principal (utilisateur authentifié) de l'objet Authentication
        User principal = (User) authentication.getPrincipal();
        // Appelle la méthode generateTokenWithUserName() avec le nom d'utilisateur
        return generateTokenWithUserName(principal.getUsername());
    }

    // Génère un token JWT avec le nom d'utilisateur spécifié
    public String generateTokenWithUserName(String username) throws JsonProcessingException {

        UserDto userDto = this.userService.findByUsername(username);

        // Crée un ensemble de revendications (claims) pour le token JWT
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") // Émetteur du token (peut être personnalisé)
                .issuedAt(Instant.now()) // Heure d'émission du token (actuelle)
                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis)) // Heure d'expiration
                .subject(username) // Sujet (nom d'utilisateur)
                .claim("user", objectMapper.writeValueAsString(userDto))
                .claim("scope", "ROLE_USER") // Revendication personnalisée (dans ce cas, "scope" est défini sur "ROLE_USER")
                .build();

        // Encode les revendications en utilisant JwtEncoder pour générer le token JWT
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    // Récupère la durée de validité du token JWT en millisecondes
    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}