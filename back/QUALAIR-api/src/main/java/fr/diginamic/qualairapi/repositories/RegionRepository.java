package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}