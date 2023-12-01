package fr.diginamic.qualairapi.dtos;

import fr.diginamic.qualairapi.entities.Address;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Address}
 */
public record AddressDto(
        Long id,
        LocalDateTime creationDate,
        LocalDateTime updateDate,
        String ligne1,
        String ligne2,
        Set<Long> userIds,
        Long cityId,
        Long zipId,
        String fullAddress
) implements SimpleEntityDto<Address> {
}