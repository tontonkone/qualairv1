package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Pollutant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollutantRepository extends JpaRepository<Pollutant, Long> {
}