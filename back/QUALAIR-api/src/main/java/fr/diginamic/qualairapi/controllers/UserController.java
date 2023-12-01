package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.auth.UserCreationDto;
import fr.diginamic.qualairapi.dtos.UserDto;
import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour les {@link User}
 */
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST},
        allowedHeaders = {"header1", "header2"})
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;


    /**
     * Requête POST permettant de créer un nouvel utilisateur.
     *
     * @param userCreationDto les informations du nouvel utilisateur
     * @return Une response HTTP contenant les informations de la nouvelle entité utilisateur
     */
    @PostMapping
    public ResponseEntity<UserDto> postNewUser (@RequestBody UserCreationDto userCreationDto) {
        UserDto userDto = userService.createUser(userCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    /**
     * Requête PUT permettant de mettre à jour un utilisateur.
     *
     * @param userDto les informations à mettre à jour
     * @return Une réponse HTTP contenant les nouvelles informations de l'utilisateur
     */
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        try {
            UserDto updatedUserDto = userService.updateUser(userDto);
            return ResponseEntity.ok().body(updatedUserDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    /**
     * Requête GET permettant de récupérer tous les utilisateurs
     *
     * @return Une réponse HTTP contenant les informations de tous les utilisateurs
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    /**
     * Requête GET permettant de récupérer un utilisateur par son id
     * @param id l'id de l'utilisateur à récupérer
     * @return Une réponse HTTP contenant les informations de l'utilisateur
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable long id) {
        try {
            UserDto user = userService.findById(id);
            return ResponseEntity.ok().body(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }
}
