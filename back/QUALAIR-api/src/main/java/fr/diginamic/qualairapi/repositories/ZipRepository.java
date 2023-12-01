package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Zip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipRepository extends JpaRepository<Zip, Long> {
}