package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlerteRepository extends JpaRepository<Alerte, Long> {
}