package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.WeatherForecast;

import java.time.LocalDateTime;

/**
 * DTO for {@link WeatherForecast}
 */
public record WeatherForecastDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        Double temperature,
        Double humidity,
        Double cloudcover,
        Long cityId
) implements SimpleEntityDto<WeatherForecast> {
}