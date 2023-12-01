package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.SimpleEntityDto;
import fr.diginamic.qualairapi.entities.BaseEntity;
import fr.diginamic.qualairapi.services.mappers.SimpleEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * L'interface SimpleEntityCreator fournit des méthodes pour créer de nouvelles entités simples
 * en utilisant un JpaRepository et un SimpleEntityMapper. Elle définit une méthode pour créer une nouvelle entité
 * en utilisant les données d'un objet SimpleEntityDto.
 *
 * @param <T> Le type de BaseEntity sur lequel le créateur d'entité opère.
 */
public interface SimpleEntityCreator<T extends BaseEntity, DTO extends SimpleEntityDto<T>> {

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
     * Crée une nouvelle entité en utilisant les données de l'objet SimpleEntityDto fourni.
     *
     * @param dto L'objet SimpleEntityDto contenant les données de création.
     * @return L'objet SimpleEntityDto représentant la nouvelle entité créée.
     */
    default DTO create(DTO dto) {
        SimpleEntityMapper<T, DTO> mapper = getMapper();
        T entity = mapper.dtoToEntity(dto);
        getRepository().save(entity);
        return mapper.entityToDto(entity);
    }
}

