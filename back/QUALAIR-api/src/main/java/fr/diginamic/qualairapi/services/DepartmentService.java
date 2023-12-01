package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.DepartmentDto;
import fr.diginamic.qualairapi.entities.Department;

public interface DepartmentService
    extends SimpleEntityCreator<Department, DepartmentDto>,
            SimpleEntityUpdater<Department, DepartmentDto>,
            SimpleEntityFinder<Department, DepartmentDto>,
            SimpleEntityDeleter<Department> {
}
