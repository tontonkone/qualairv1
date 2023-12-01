package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.WeatherForecastDto;
import fr.diginamic.qualairapi.entities.City;

public interface WeatherForecastService {

    WeatherForecastDto getCurrent(City city);
}
