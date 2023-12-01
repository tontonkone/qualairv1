package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.repositories.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SchedulerTest {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void every1hour() {
        City nantes = new City();
        nantes.setName("Nantes");
        cityRepository.save(nantes);

        City paris = new City();
        paris.setName("Paris");
        paris.setWaqiId(5722);
        cityRepository.save(paris);

        scheduler.every1hour();
    }

}