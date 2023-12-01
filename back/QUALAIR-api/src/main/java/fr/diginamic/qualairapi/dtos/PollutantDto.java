package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Pollutant;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Pollutant}
 */
public record PollutantDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        Integer code,
        String name,
        Set<Long> bookmarkIds
) implements SimpleEntityDto<Pollutant> {
}