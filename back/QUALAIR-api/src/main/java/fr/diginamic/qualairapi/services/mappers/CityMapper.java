package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.CityDto;
import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.entities.Department;
import fr.diginamic.qualairapi.entities.Zip;
import fr.diginamic.qualairapi.repositories.DepartmentRepository;
import fr.diginamic.qualairapi.repositories.ZipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityMapper implements SimpleEntityMapper<City, CityDto> {
    private final DepartmentRepository departmentRepository;
    private final ZipRepository zipRepository;

    @Override
    public City dtoToEntity(CityDto dto) {
        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new RuntimeException("Department with id " + dto.departmentId() + "doesn't exist"));
        Set<Zip> zips = dto.zipIds().stream()
                .map(id -> zipRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Region with id " + id + "doesn't exist"))
                )
                .collect(Collectors.toSet());

        City city = new City();

        city.setId(dto.id());
        city.setCreationDate(dto.creationDate());
        city.setUpdateDate(dto.updateDate());
        city.setInseeCode(dto.inseeCode());
        city.setName(dto.name());
        city.setPopulation(dto.population());
        city.setDepartment(department);
        city.setZips(zips);
        city.setWaqiId(dto.waqiId());

        return city;
    }

    @Override
    public CityDto entityToDto(City city) {
        return new CityDto(
                city.getId(),
                city.getCreationDate(),
                city.getUpdateDate(),
                city.getInseeCode(),
                city.getName(),
                city.getPopulation(),
                city.getDepartment().getId(),
                city.getZips().stream()
                        .map(Zip::getId)
                        .collect(Collectors.toSet()),
                city.getWaqiId()
        );
    }

    @Override
    public void updateEntityWithDto(CityDto dto, City city) {
        if (null != dto.departmentId()) {
            Department department = departmentRepository.findById(dto.departmentId())
                    .orElseThrow(() -> new RuntimeException("Department with id " + dto.departmentId() + "doesn't exist"));
            city.setDepartment(department);
        }
        if (null != dto.zipIds()) {
            Set<Zip> zips = dto.zipIds().stream()
                    .map(id -> zipRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Region with id " + id + "doesn't exist")))
                    .collect(Collectors.toSet());
            city.setZips(zips);
        }

        if (null != dto.inseeCode()) {
            city.setInseeCode(dto.inseeCode());
        }
        if (null != dto.name()) {
            city.setName(dto.name());
        }
        if (null != dto.population()) {
            city.setPopulation(dto.population());
        }
        if (null != dto.waqiId()) {
            city.setWaqiId(dto.waqiId());
        }
    }
}
