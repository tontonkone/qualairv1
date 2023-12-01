package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Region;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link fr.diginamic.qualairapi.entities.Region}
 */
public record RegionDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        String name,
        Integer population,
        Set<Long> departmentIds
) implements SimpleEntityDto<Region> {
}