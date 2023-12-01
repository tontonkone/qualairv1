package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.entities.AirQuality;
import fr.diginamic.qualairapi.entities.WeatherForecast;
import fr.diginamic.qualairapi.repositories.AirQualityRepository;
import fr.diginamic.qualairapi.repositories.CityRepository;
import fr.diginamic.qualairapi.repositories.WeatherForecastRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de planification (scheduler) pour exécuter des tâches à intervalles réguliers.
 */
@RequiredArgsConstructor
@Service
public class Scheduler {
    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    /**
     * Repository pour les opérations liées aux villes.
     */
    private final CityRepository cityRepository;

    /**
     * Service pour les opérations liées aux villes.
     */
    private final CityService cityService;

    /**
     * Repository pour les opérations liées aux prévisions météorologiques.
     */
    private final WeatherForecastRepository weatherForecastRepository;
    private final AirQualityRepository airQualityRepository;

    /**
     * Exécute la récupération des données de recensement pour toutes les villes enregistrées en base, régulièrement.
     */
//    @Scheduled(cron = "0 0 1 * * *")
    // Actuellement configurer sur toutes les heures pour des raisons de tests
    @Scheduled(cron = "0 0 0/1 * * *")
    public void allDays() {
        // RG : Récupération des données de recensement pour toutes les villes enregistrées en base régulièrement.
        cityRepository.findAll().forEach(city -> {
            cityService.fetchCityPopulation(city);
            cityRepository.save(city);
        });
    }

    /**
     * Exécute la récupération des conditions météo et des données de qualité de l'air en temps réel pour toutes les villes enregistrées en base, toutes les heures.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Scheduled(cron = "0 0 0/1 * * *")
    public void every1hour() {
        log.info("Démarrage des tâche planifier toute les heures");
        cityRepository.findAll().forEach(city -> {
            // RG : Récupération des conditions météo en temps réel pour toutes les villes en base de données toutes les heures
            WeatherForecast weatherForecast = cityService.fetchWeatherForecast(city);
            weatherForecastRepository.save(weatherForecast);

            // RG : Récupération des données de qualité de l'air en temps réel pour toutes les villes en base de données toutes les heures
            AirQuality airQuality = cityService.fetchAirQuality(city);
            airQualityRepository.save(airQuality);
        });
    }
}
