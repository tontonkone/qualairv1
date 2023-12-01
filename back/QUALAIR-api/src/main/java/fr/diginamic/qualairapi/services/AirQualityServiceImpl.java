package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.repositories.AirQualityRepository;
import fr.diginamic.qualairapi.services.mappers.AirQualityMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class AirQualityServiceImpl implements AirQualityService {
    private final AirQualityRepository repository;
    private final AirQualityMapper mapper;
}
