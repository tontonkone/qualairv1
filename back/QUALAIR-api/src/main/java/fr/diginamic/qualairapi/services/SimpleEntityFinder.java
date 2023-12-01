package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.entities.BaseEntity;
import fr.diginamic.qualairapi.dtos.SimpleEntityDto;
import fr.diginamic.qualairapi.services.mappers.SimpleEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * L'interface SimpleEntityFinder fournit des méthodes pour récupérer et mapper les données d'entités simples
 * en utilisant un JpaRepository et un SimpleEntityMapper. Elle définit des méthodes pour trouver toutes les entités
 * et pour trouver une entité par son ID.
 *
 * @param <T> Le type de BaseEntity sur lequel le chercheur opère.
 */
public interface SimpleEntityFinder<T extends BaseEntity, DTO extends SimpleEntityDto<T>> {

    /**
     * Récupère le JpaRepository associé au type d'entité.
     *
     * @return L'instance JpaRepository pour le type d'entité.
     */
    JpaRepository<T, Long> getRepository();

    /**
     * Récupère le SimpleEntityMapper associé au type d'entité.
     *
     * @return L'instance SimpleEntityMapper pour le type d'entité.
     */
    SimpleEntityMapper<T, DTO> getMapper();

    /**
     * Récupère une liste d'objets SimpleEntityDto représentant toutes les entités du type spécifié.
     *
     * @return Une liste d'objets SimpleEntityDto.
     */
    default List<DTO> findAll() {
        return getRepository().findAll().stream()
                .map(getMapper()::entityToDto)
                .toList();
    }

    /**
     * Récupère un objet SimpleEntityDto représentant l'entité avec l'ID spécifié.
     *
     * @param id L'ID de l'entité à récupérer.
     * @return L'objet SimpleEntityDto représentant l'entité.
     * @throws RuntimeException si l'entité avec l'ID donné n'est pas trouvée.
     */
    default DTO findById(long id) {
        return getRepository().findById(id)
                .map(getMapper()::entityToDto)
                .orElseThrow(() -> new RuntimeException("Entité avec l'ID " + id + " introuvable."));
    }
}
