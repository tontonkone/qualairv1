package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.auth.UserCreationDto;
import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.dtos.UserDto;
import fr.diginamic.qualairapi.repositories.UserRepository;
import fr.diginamic.qualairapi.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service métier pour les {@link User}
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Création d'une nouvelle entité utilisateur à partir d'information de création.
     *
     * @param userCreationDto les informations de création du nouvel utilisateur
     * @return les informations de l'entité utilisateur créée
     */
    public UserDto createUser(UserCreationDto userCreationDto) {
        User user = userMapper.creationDtoToEntity(userCreationDto);
        userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    /**
     * Mise à jour d'une entité utilisateur.
     *
     * @param userDto les informations à mettre à jour
     * @return les informations de l'utilisateur mis à jour
     */
    public UserDto updateUser(UserDto userDto) {
        return userRepository.findById(userDto.getId())
                .map(user -> {
                    userMapper.updateEntityWithDto(userDto, user);
                    userRepository.save(user);
                    return userMapper.entityToDto(user);
                })
                .orElseThrow(() -> new RuntimeException("User Not Found."));
    }

    /**
     * Récupère tous les utilisateurs.
     *
     * @return les informations de tous les utilisateurs
     */
    public List<UserDto> findAll () {
        return userRepository.findAll().stream()
                .map(userMapper::entityToDto)
                .toList();
    }

    /**
     * Récupère un utilisateur par son id.
     * @param id l'id de l'utilisateur à récupérer
     * @return les informations de l'utilisateur
     */
    public UserDto findById(long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("User Not Found."));
    }

    public UserDto findByUsername(String username) {
        return userRepository.findUserByEmail(username)
                .map(userMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("User Not Found."));
    }
}
