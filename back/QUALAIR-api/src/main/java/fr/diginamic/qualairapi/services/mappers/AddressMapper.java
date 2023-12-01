package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.AddressDto;
import fr.diginamic.qualairapi.entities.*;
import fr.diginamic.qualairapi.repositories.CityRepository;
import fr.diginamic.qualairapi.repositories.UserRepository;
import fr.diginamic.qualairapi.repositories.ZipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressMapper implements SimpleEntityMapper<Address, AddressDto> {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final ZipRepository zipRepository;

    @Override
    public Address dtoToEntity(AddressDto dto) {
        Set<User> users = null;
        if (null != dto.userIds()) {
            users = dto.userIds().stream()
                    .map(id -> userRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("No User with id " + id + " found.")))
                    .collect(Collectors.toSet());
        }
        City city = getRelatedCityFromDto(dto);
        Zip zip = getRelatedZipFromDto(dto);

        Address address = new Address();

        address.setId(dto.id());
        address.setLigne1(dto.ligne1());
        address.setLigne2(dto.ligne2());
        address.setUsers(users);
        address.setCity(city);
        address.setZip(zip);

        return address;
    }

    @Override
    public AddressDto entityToDto(Address address) {
        String fullAddress = address.getLigne1() +
                " ," + address.getLigne2() +
                " ," + address.getZip().getCode() +
                " " + address.getCity().getName().toUpperCase();
        return new AddressDto(
                address.getId(),
                address.getCreationDate(),
                address.getUpdateDate(),
                address.getLigne1(),
                address.getLigne2(),
                address.getUsers().stream()
                        .map(User::getId)
                        .collect(Collectors.toSet()),
                address.getCity().getId(),
                address.getZip().getId(),
                fullAddress
        );
    }

    @Override
    public void updateEntityWithDto(AddressDto dto, Address address) {
        City city = getRelatedCityFromDto(dto);
        Zip zip = getRelatedZipFromDto(dto);

        if (null != dto.ligne1()) {
            address.setLigne1(dto.ligne1());
        }
        if (null != dto.ligne2()) {
            address.setLigne2(dto.ligne2());
        }
        if (null != city) {
            address.setCity(city);
        }
        if (null != zip) {
            address.setZip(zip);
        }
    }

    private City getRelatedCityFromDto(AddressDto dto) {
        City city = null;
        if (null != dto.cityId()) {
            city = cityRepository.findById(dto.cityId())
                    .orElseThrow(() -> new RuntimeException("No City with id " + dto.cityId() + " found."));
        }
        return city;
    }

    private Zip getRelatedZipFromDto(AddressDto dto) {
        Zip zip = null;
        if (null != dto.zipId()) {
            zip = zipRepository.findById(dto.zipId())
                    .orElseThrow(() -> new RuntimeException("No Zip with id " + dto.zipId() + " found."));
        }
        return zip;
    }
}
