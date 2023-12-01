package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}