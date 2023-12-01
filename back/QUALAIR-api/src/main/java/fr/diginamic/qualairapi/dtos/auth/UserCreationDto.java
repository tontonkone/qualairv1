package fr.diginamic.qualairapi.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * DTO for {@link fr.diginamic.qualairapi.entities.User}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreationDto {
    @NotBlank(message = "firstname is required")
    String firstName;
    @NotBlank(message = "Lastname is required")
    String lastName;
    @Email
    @NotEmpty(message = "Email is required")
    String email;
    @NotBlank(message = "Password is required")
    String password;
}