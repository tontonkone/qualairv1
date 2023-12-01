package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.AirQuality;
import fr.diginamic.qualairapi.entities.Index;
import fr.diginamic.qualairapi.entities.IndexType;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO for {@link AirQuality}
 */
public record AirQualityDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        IndexType type,
        Index index,
        Map<Long, Index> underindexesByPollutantId
) implements SimpleEntityDto<AirQuality> {
}