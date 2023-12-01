package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.entities.AirQuality;
import fr.diginamic.qualairapi.dtos.AirQualityDto;
import fr.diginamic.qualairapi.entities.Index;
import fr.diginamic.qualairapi.entities.Pollutant;
import fr.diginamic.qualairapi.repositories.PollutantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AirQualityMapper implements SimpleEntityMapper<AirQuality, AirQualityDto> {
    private final PollutantRepository pollutantRepository;

    @Override
    public AirQuality dtoToEntity(AirQualityDto dto) {
        Map<Pollutant, Index> underindexes = getRelatedPollutantIndexMapFromDto(dto);

        AirQuality airQuality = new AirQuality();

        airQuality.setId(dto.id());
        airQuality.setType(dto.type());
        airQuality.setIndex(dto.index());
        airQuality.setUnderindexes(underindexes);

        return airQuality;
    }

    @Override
    public AirQualityDto entityToDto(AirQuality airQuality) {
        return new AirQualityDto(
                airQuality.getId(),
                airQuality.getCreationDate(),
                airQuality.getUpdateDate(),
                airQuality.getType(),
                airQuality.getIndex(),
                airQuality.getUnderindexes().entrySet().stream()
                        .flatMap(pollutantIndexEntry ->
                                Map.of(pollutantIndexEntry.getKey().getId(), pollutantIndexEntry.getValue())
                                        .entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
    }

    @Override
    public void updateEntityWithDto(AirQualityDto dto, AirQuality airQuality) {
        Map<Pollutant, Index> underindexes = getRelatedPollutantIndexMapFromDto(dto);

        if (null != dto.type()) {
            airQuality.setType(dto.type());
        }
        if (null != dto.index()) {
            airQuality.setIndex(dto.index());
        }
        if (null != underindexes) {
            airQuality.setUnderindexes(underindexes);
        }
    }

    private Map<Pollutant, Index> getRelatedPollutantIndexMapFromDto(AirQualityDto dto) {
        Map<Pollutant, Index> underindexes = null;
        if (null != dto.underindexesByPollutantId() && !dto.underindexesByPollutantId().isEmpty()) {
            underindexes = dto.underindexesByPollutantId().entrySet().stream()
                    .flatMap(longIndexEntry -> {
                        Pollutant pollutant = pollutantRepository.findById(longIndexEntry.getKey())
                                .orElseThrow(() -> new RuntimeException("No Pollutant with id " +
                                        longIndexEntry.getKey() + " found."));
                        return Map.of(pollutant, longIndexEntry.getValue()).entrySet().stream();
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return underindexes;
    }
}
