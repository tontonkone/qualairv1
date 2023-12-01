package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.WeatherForecastDto;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.entities.WeatherForecast;
import fr.diginamic.qualairapi.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeatherForecastMapper implements SimpleEntityMapper<WeatherForecast, WeatherForecastDto> {
    private final CityRepository cityRepository;
    @Override
    public WeatherForecast dtoToEntity(WeatherForecastDto dto) {
        City city = getRelatedCityFromDto(dto);

        WeatherForecast weatherForecast = new WeatherForecast();

        weatherForecast.setId(dto.id());
        weatherForecast.setTemperature(dto.temperature());
        weatherForecast.setHumidity(dto.humidity());
        weatherForecast.setCloudcover(dto.cloudcover());
        weatherForecast.setCity(city);

        return weatherForecast;
    }

    @Override
    public WeatherForecastDto entityToDto(WeatherForecast weatherForecast) {
        return new WeatherForecastDto(
                weatherForecast.getId(),
                weatherForecast.getCreationDate(),
                weatherForecast.getUpdateDate(),
                weatherForecast.getTemperature(),
                weatherForecast.getHumidity(),
                weatherForecast.getCloudcover(),
                weatherForecast.getCity().getId()
        );
    }

    @Override
    public void updateEntityWithDto(WeatherForecastDto dto, WeatherForecast weatherForecast) {
        City city = getRelatedCityFromDto(dto);

        if (null != dto.temperature()) {
            weatherForecast.setTemperature(dto.temperature());
        }
        if (null != dto.humidity()) {
            weatherForecast.setHumidity(dto.humidity());
        }
        if (null != city) {
            weatherForecast.setCity(city);
        }
    }

    private City getRelatedCityFromDto(WeatherForecastDto dto) {
        City city = null;
        if (null != dto.cityId()) {
            city = cityRepository.findById(dto.cityId())
                    .orElseThrow(() -> new RuntimeException("No City with id " + dto.cityId() + " found."));
        }
        return city;
    }
}
