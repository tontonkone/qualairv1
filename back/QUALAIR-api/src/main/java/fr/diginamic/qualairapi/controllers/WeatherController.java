package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.WeatherForecastDto;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.services.CityService;
import fr.diginamic.qualairapi.services.WeatherForecastService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("weather")
public class WeatherController {
    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherForecastService weatherForecastService;
    private final CityService cityService;

    @RequestMapping("current")
    @ResponseStatus(HttpStatus.OK)
    public WeatherForecastDto getCurrent(@RequestParam("city") String cityName) {
        log.info("Requete reçue pour récuprer les données de météo courante pour la ville {}", cityName);
        if (cityName == null) {
            throw new RuntimeException("cityId must be defined");
        }

        City city = cityService.findByName(cityName);
        return this.weatherForecastService.getCurrent(city);
    }
}
