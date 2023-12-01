package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.repositories.ThreadRepository;
import fr.diginamic.qualairapi.services.mappers.ThreadMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class ThreadServiceImpl implements ThreadService {
    private final ThreadRepository repository;
    private final ThreadMapper mapper;
}
