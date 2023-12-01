package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.CityDto;
import fr.diginamic.qualairapi.entities.AirQuality;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.entities.WeatherForecast;

public interface CityService
    extends SimpleEntityCreator<City, CityDto>,
            SimpleEntityUpdater<City, CityDto>,
            SimpleEntityFinder<City, CityDto>,
            SimpleEntityDeleter<City> {
    void fetchCityPopulation(City city);

    WeatherForecast fetchWeatherForecast(City city);

    AirQuality fetchAirQuality(City city);

    City findByName(String city);
}
