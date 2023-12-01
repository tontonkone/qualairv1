package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
}