package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}