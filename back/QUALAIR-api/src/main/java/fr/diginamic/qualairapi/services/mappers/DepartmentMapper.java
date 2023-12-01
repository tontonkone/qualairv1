package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.DepartmentDto;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.entities.Department;
import fr.diginamic.qualairapi.entities.Region;
import fr.diginamic.qualairapi.repositories.CityRepository;
import fr.diginamic.qualairapi.repositories.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentMapper implements SimpleEntityMapper<Department, DepartmentDto> {
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    @Override
    public Department dtoToEntity(DepartmentDto dto) {
        Region region = regionRepository.findById(dto.regionId())
                .orElseThrow(() -> new RuntimeException("Region with id " + dto.regionId() + "doesn't exist"));

        Department department = new Department();

        department.setId(dto.id());
        department.setCreationDate(dto.creationDate());
        department.setUpdateDate(dto.updateDate());
        department.setCode(dto.code());
        department.setName(dto.name());
        department.setPopulation(dto.population());
        department.setRegion(region);

        return department;
    }

    @Override
    public DepartmentDto entityToDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getCreationDate(),
                department.getUpdateDate(),
                department.getCode(),
                department.getName(),
                department.getPopulation(),
                department.getCities().stream()
                        .map(City::getId)
                        .collect(Collectors.toSet()),
                department.getRegion().getId()
        );
    }

    @Override
    public void updateEntityWithDto(DepartmentDto dto, Department department) {
        if (null != dto.regionId()) {
            Region region = regionRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Region with id " + dto.regionId() + "doesn't exist"));
            department.setRegion(region);
        }
        if (null != dto.cityIds()) {
            Set<City> cities = dto.cityIds().stream()
                    .map(id -> cityRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("No City with id " + id + " found.")))
                    .collect(Collectors.toSet());
            department.setCities(cities);
        }

        if (null != dto.code()) {
            department.setCode(dto.code());
        }
        if (null != dto.name()) {
            department.setName(dto.name());
        }
        if (null != dto.population()) {
            department.setPopulation(dto.population());
        }
    }
}
