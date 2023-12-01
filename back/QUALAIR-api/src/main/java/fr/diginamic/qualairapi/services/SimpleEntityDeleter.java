package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface définissant un moyen générique de supprimer des entités basées sur un DTO (Data Transfer Object) associé.
 *
 * @param <T> Le type d'entité étendant BaseEntity.
 */
public interface SimpleEntityDeleter<T extends BaseEntity> {

    /**
     * Récupère le JpaRepository associé au type d'entité.
     *
     * @return L'instance JpaRepository pour le type d'entité.
     */
    JpaRepository<T, Long> getRepository();

    /**
     * Supprime l'entité correspondante au DTO spécifié.
     *
     * @param id L'id de l'entité à supprimer.
     * @throws RuntimeException Si l'entité n'est pas trouvée dans la base de données.
     */
    default void delete(Long id) throws RuntimeException {
        JpaRepository<T, Long> repository = getRepository();
        T entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entité non trouvée."));
        repository.delete(entity);
    }
}
