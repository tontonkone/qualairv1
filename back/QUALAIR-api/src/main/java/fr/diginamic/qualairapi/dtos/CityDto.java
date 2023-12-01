package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.City;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link fr.diginamic.qualairapi.entities.City}
 */
public record CityDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        Long inseeCode,
        String name,
        Long population,
        Long departmentId,
        Set<Long> zipIds,
        Integer waqiId) implements SimpleEntityDto<City> {
}