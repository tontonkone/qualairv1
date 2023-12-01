package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}