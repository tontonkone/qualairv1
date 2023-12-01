package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.AddressDto;
import fr.diginamic.qualairapi.entities.Address;

public interface AddressService
        extends SimpleEntityCreator<Address, AddressDto>,
                SimpleEntityUpdater<Address, AddressDto>,
                SimpleEntityFinder<Address, AddressDto>,
                SimpleEntityDeleter<Address> {
}
