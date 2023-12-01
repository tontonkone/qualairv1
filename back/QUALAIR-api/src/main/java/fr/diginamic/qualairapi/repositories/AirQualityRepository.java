package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {
}