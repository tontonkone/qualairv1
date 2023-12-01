package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.SimpleEntityDto;
import fr.diginamic.qualairapi.entities.BaseEntity;
import fr.diginamic.qualairapi.services.mappers.SimpleEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * L'interface SimpleEntityUpdater fournit des méthodes pour mettre à jour les entités simples
 * en utilisant un JpaRepository et un SimpleEntityMapper. Elle définit une méthode pour mettre à jour une entité
 * à l'aide d'un objet SimpleEntityDto.
 *
 * @param <T> Le type de BaseEntity sur lequel le metteur à jour opère.
 */
public interface SimpleEntityUpdater<T extends BaseEntity, DTO extends SimpleEntityDto<T>> {

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
     * Met à jour l'entité en utilisant les données de l'objet SimpleEntityDto fourni.
     *
     * @param dto L'objet SimpleEntityDto contenant les données de mise à jour.
     * @return L'objet SimpleEntityDto représentant l'entité mise à jour.
     * @throws RuntimeException si l'entité avec l'ID du DTO n'est pas trouvée.
     */
    default DTO update(DTO dto) {
        return getRepository().findById(dto.id())
                .map(entity -> {
                    SimpleEntityMapper<T, DTO> mapper = getMapper();
                    mapper.updateEntityWithDto(dto, entity);
                    getRepository().save(entity);
                    return mapper.entityToDto(entity);
                })
                .orElseThrow(() -> new RuntimeException("Entité non trouvée."));
    }
}
