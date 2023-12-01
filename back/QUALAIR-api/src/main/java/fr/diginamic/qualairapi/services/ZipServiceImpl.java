package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.repositories.ZipRepository;
import fr.diginamic.qualairapi.services.mappers.ZipMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class ZipServiceImpl implements ZipService {
    private final ZipRepository repository;
    private final ZipMapper mapper;
}
