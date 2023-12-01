package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Zip;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link fr.diginamic.qualairapi.entities.Zip}
 */
public record ZipDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        Integer code,
        Set<Long> addressIds,
        Set<Long> cityIds
) implements SimpleEntityDto<Zip> {
}