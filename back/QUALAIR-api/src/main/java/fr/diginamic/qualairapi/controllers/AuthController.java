package fr.diginamic.qualairapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.diginamic.qualairapi.dtos.auth.UserCreationDto;
import fr.diginamic.qualairapi.dtos.auth.AuthenticationResponse;
import fr.diginamic.qualairapi.dtos.auth.LoginRequest;
import fr.diginamic.qualairapi.dtos.auth.RefreshTokenRequest;
import fr.diginamic.qualairapi.exceptions.DuplicateEntryException;
import fr.diginamic.qualairapi.services.authService.AuthService;
import fr.diginamic.qualairapi.services.authService.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST},
        allowedHeaders = {"header1", "header2"})

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCreationDto userCreationDto) {
        try{
            authService.signup(userCreationDto);
            return new ResponseEntity<>("User Registration Successful",
                    OK);
        } catch (DuplicateEntryException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws Exception {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
