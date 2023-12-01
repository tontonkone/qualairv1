package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.AddressDto;
import fr.diginamic.qualairapi.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur de l'entité "Address" qui gère les opérations CRUD (Création, Lecture, Mise à jour, Suppression) pour les adresses.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("address")
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    /**
     * Récupère toutes les adresses.
     *
     * @return Liste des enregistrements {@link AddressDto} représentant toutes les adresses.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AddressDto> findAll() {
        logger.info("Requête reçue pour récupérer toutes les adresses.");

        List<AddressDto> addresses = addressService.findAll();

        logger.info("{} adresses retournées.", addresses.size());
        return addresses;
    }

    /**
     * Récupère une adresse par son ID.
     *
     * @param id L'ID de l'adresse à récupérer.
     * @return L'enregistrement {@link AddressDto} représentant l'adresse trouvée, ou {@code null} si aucune adresse n'est trouvée.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto findById(@PathVariable Long id) {
        logger.info("Requête reçue pour récupérer l'adresse avec l'ID : {}", id);

        AddressDto address = addressService.findById(id);

        if (address != null) {
            logger.info("Adresse trouvée : {}", address.fullAddress());
        } else {
            logger.warn("Aucune adresse trouvée avec l'ID : {}", id);
        }

        return address;
    }

    /**
     * Crée une nouvelle adresse.
     *
     * @param addressDto Les informations de la nouvelle adresse à créer.
     * @return L'enregistrement {@link AddressDto} représentant l'adresse créée.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto create(@RequestBody AddressDto addressDto) {
        logger.info("Requête reçue pour créer une nouvelle adresse : {}", addressDto);

        AddressDto createdAddress = addressService.create(addressDto);

        logger.info("Nouvelle adresse créée avec l'ID : {}", createdAddress.id());
        return createdAddress;
    }

    /**
     * Met à jour une adresse existante.
     *
     * @param addressDto Les nouvelles informations de l'adresse à mettre à jour.
     * @return L'enregistrement {@link AddressDto} représentant l'adresse mise à jour, ou {@code null} si la mise à jour a échoué.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AddressDto update(@RequestBody AddressDto addressDto) {
        logger.info("Requête reçue pour mettre à jour l'adresse avec l'ID : {}", addressDto.id());

        AddressDto updatedAddress = addressService.update(addressDto);

        if (updatedAddress != null) {
            logger.info("Adresse mise à jour avec succès : {}", updatedAddress);
        } else {
            logger.warn("Impossible de mettre à jour l'adresse avec l'ID : {}", addressDto.id());
        }

        return updatedAddress;
    }

    /**
     * Supprime une adresse par son ID.
     *
     * @param id L'ID de l'adresse à supprimer.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        logger.info("Requête reçue pour supprimer l'adresse avec l'ID : {}", id);

        addressService.delete(id);

        logger.info("Adresse supprimée avec succès : {}", id);
    }
}
