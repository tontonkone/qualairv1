package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.repositories.DepartmentRepository;
import fr.diginamic.qualairapi.services.mappers.DepartmentMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
}
