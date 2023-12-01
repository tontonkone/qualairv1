package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Post;
import fr.diginamic.qualairapi.entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Post}
 */
public record PostDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        UserDto author,
        Long threadId,
        String content
) implements SimpleEntityDto<Post> {
    /**
     * DTO for {@link User}
     */
    public record UserDto(
            Long id,
            String firstName,
            String lastName,
            String email
    ) implements Serializable {
    }
}