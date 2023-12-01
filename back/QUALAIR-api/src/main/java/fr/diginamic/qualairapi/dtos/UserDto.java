package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Role;
import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.entities.UserStatus;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    LocalDateTime createDate;
    LocalDateTime updateDate;
    String firstName;
    String lastName;
    String email;
    Role role;
    UserStatus userStatus;
}