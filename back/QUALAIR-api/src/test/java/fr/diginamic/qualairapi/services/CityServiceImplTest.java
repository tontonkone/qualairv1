package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.entities.City;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityServiceImplTest {

    @Autowired
    private CityServiceImpl cityService;

    @Test
    void fetchCityPopulation() {
        City city = new City();
        city.setName("Nantes");
        city.setInseeCode(44109L);

        cityService.fetchCityPopulation(city);
    }

    @Test
    void fetchWeatherForecast() {
        City city = new City();
        city.setName("Nantes");
        cityService.fetchWeatherForecast(city);
    }

    @Test
    void fetchAirQuality(){
        City city = new City();
        city.setName("Nantes");
        cityService.fetchAirQuality(city);
    }
}