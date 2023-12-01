package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.repositories.AddressRepository;
import fr.diginamic.qualairapi.services.mappers.AddressMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private final AddressMapper mapper;
}
