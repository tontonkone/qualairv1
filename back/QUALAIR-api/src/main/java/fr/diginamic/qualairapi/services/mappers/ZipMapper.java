package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.ZipDto;
import fr.diginamic.qualairapi.entities.Address;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.entities.Zip;
import fr.diginamic.qualairapi.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ZipMapper implements SimpleEntityMapper<Zip, ZipDto> {
    private final CityRepository cityRepository;

    @Override
    public Zip dtoToEntity(ZipDto dto) {
        Set<City> cities = dto.cityIds().stream()
                .map(id -> cityRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("No city with id " + id + " found.")))
                .collect(Collectors.toSet());

        Zip zip = new Zip();

        zip.setId(dto.id());
        zip.setCode(dto.code());
        zip.setCities(cities);

        return zip;
    }

    @Override
    public ZipDto entityToDto(Zip zip) {
        return new ZipDto(
                zip.getId(),
                zip.getCreationDate(),
                zip.getUpdateDate(),
                zip.getCode(),
                zip.getAddresses().stream()
                        .map(Address::getId)
                        .collect(Collectors.toSet()),
                zip.getCities().stream()
                        .map(City::getId)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public void updateEntityWithDto(ZipDto dto, Zip zip) {
        if (null != dto.cityIds()) {
            Set<City> cities = dto.cityIds().stream()
                    .map(id -> cityRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("No city with id " + id + " found.")))
                    .collect(Collectors.toSet());
            zip.setCities(cities);
        }

        if (null != dto.code()) {
            zip.setCode(dto.code());
        }
    }
}
