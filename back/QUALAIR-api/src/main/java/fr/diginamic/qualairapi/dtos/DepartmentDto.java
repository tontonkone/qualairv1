package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Department;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Department}
 */
public record DepartmentDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        String code,
        String name,
        Integer population,
        Set<Long> cityIds,
        Long regionId
    ) implements SimpleEntityDto<Department> {
}