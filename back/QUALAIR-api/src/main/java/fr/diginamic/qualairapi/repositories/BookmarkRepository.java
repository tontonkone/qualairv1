package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}