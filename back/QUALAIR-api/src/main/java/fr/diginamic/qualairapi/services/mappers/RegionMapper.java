package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.RegionDto;
import fr.diginamic.qualairapi.entities.Department;
import fr.diginamic.qualairapi.entities.Region;
import fr.diginamic.qualairapi.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RegionMapper implements SimpleEntityMapper<Region, RegionDto> {

    private final DepartmentRepository departmentRepository;

    @Override
    public Region dtoToEntity(RegionDto dto) {
        Set<Department> departments = null;
        if (null != dto.departmentIds() && !dto.departmentIds().isEmpty()) {
            departments = dto.departmentIds().stream()
                    .map(id -> departmentRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("No Department with id " + id + " found.")))
                    .collect(Collectors.toSet());
        }
        Region region = new Region();

        region.setId(dto.id());
        region.setName(dto.name());
        region.setPopulation(dto.population());
        region.setDepartments(departments);

        return region;
    }

    @Override
    public RegionDto entityToDto(Region region) {
        return new RegionDto(
                region.getId(),
                region.getCreationDate(),
                region.getUpdateDate(),
                region.getName(),
                region.getPopulation(),
                region.getDepartments().stream()
                        .map(Department::getId)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public void updateEntityWithDto(RegionDto dto, Region region) {
        if (null != dto.departmentIds() && !dto.departmentIds().isEmpty()) {
            Set<Department> departments = dto.departmentIds().stream()
                    .map(id -> departmentRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("No Department with id " + id + " found.")))
                    .collect(Collectors.toSet());
            region.setDepartments(departments);
        }

        if (null != dto.name()) {
            region.setName(dto.name());
        }
        if (null != dto.population()) {
            region.setPopulation(dto.population());
        }
    }
}
