package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.SimpleEntityDto;
import fr.diginamic.qualairapi.entities.BaseEntity;

public interface SimpleEntityMapper<T extends BaseEntity, DTO extends SimpleEntityDto<T>> {
    T dtoToEntity(DTO dto);

    DTO entityToDto(T entity);

    void updateEntityWithDto(DTO dto, T entity);
}
