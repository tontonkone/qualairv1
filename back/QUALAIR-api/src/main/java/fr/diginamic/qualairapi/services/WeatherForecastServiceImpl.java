package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.WeatherForecastDto;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.repositories.WeatherForecastRepository;
import fr.diginamic.qualairapi.services.mappers.WeatherForecastMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {
    private final WeatherForecastRepository repository;
    private final WeatherForecastMapper mapper;

    @Override
    public WeatherForecastDto getCurrent(City city) {
        return repository.findByCityAndCreationDateOrderByCreationDateDesc(city/*, LocalDateTime.now()*/)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("WeatherForecast Not Found for this city"));
    }
}
