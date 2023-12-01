package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.BaseEntity;

/**
 * L'interface SimpleEntityDto définit les méthodes pour obtenir des informations communes
 * à toutes les entités DTO (Data Transfer Object) représentant des entités simples.
 *
 * @param <T> Le type de BaseEntity sur lequel l'entité DTO est basée.
 */
public interface SimpleEntityDto<T extends BaseEntity> {

    /**
     * Récupère l'identifiant unique de l'entité DTO.
     *
     * @return L'identifiant unique de l'entité DTO.
     */
    Long id();
}

