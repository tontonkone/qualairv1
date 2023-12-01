package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Thread;

import java.time.LocalDateTime;

/**
 * DTO for {@link Thread}
 */
public record ThreadDto (
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        String title
) implements SimpleEntityDto<Thread> {
}