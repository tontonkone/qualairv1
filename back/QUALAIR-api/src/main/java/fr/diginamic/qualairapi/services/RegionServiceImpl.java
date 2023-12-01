package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.repositories.RegionRepository;
import fr.diginamic.qualairapi.services.mappers.RegionMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository repository;
    private final RegionMapper mapper;
}
