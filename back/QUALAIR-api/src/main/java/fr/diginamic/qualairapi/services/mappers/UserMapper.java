package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.auth.UserCreationDto;
import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.dtos.UserDto;
import fr.diginamic.qualairapi.entities.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User creationDtoToEntity(UserCreationDto creationDto) {
        User user = new User();

        user.setEmail(creationDto.getEmail());
        user.setPassword(creationDto.getPassword());
        user.setLastName(creationDto.getLastName());
        user.setFirstName(creationDto.getFirstName());
        user.setUserStatus(UserStatus.ACTIVE);

        return user;
    }

    public UserDto entityToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getCreationDate(),
                user.getUpdateDate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getUserStatus()

        );
    }

    public User dtoToEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setCreationDate(userDto.getCreateDate());
        user.setUpdateDate(user.getUpdateDate());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setUserStatus(userDto.getUserStatus());
        user.setRole(userDto.getRole());
        return user;
    }

    public User updateEntityWithDto(UserDto userDto, User user) {
        if (userDto.getCreateDate() != null) {
            user.setCreationDate(userDto.getCreateDate());
        }
        if (userDto.getUserStatus() != null) {
            user.setUpdateDate(userDto.getUpdateDate());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getUserStatus() != null) {
            user.setUserStatus(userDto.getUserStatus());
        }

        return user;
    }
}