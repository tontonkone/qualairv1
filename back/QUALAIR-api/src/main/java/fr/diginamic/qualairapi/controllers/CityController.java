package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.CityDto;
import fr.diginamic.qualairapi.services.CityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur de l'entité "City" qui gère les opérations CRUD (Création, Lecture, Mise à jour, Suppression) pour les villes.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("city")
public class CityController {
    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;

    /**
     * Récupère toutes les villes.
     *
     * @return Liste des enregistrements {@link CityDto} représentant toutes les villes.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityDto> findAll() {
        logger.info("Requête reçue pour récupérer toutes les villes.");

        List<CityDto> cities = cityService.findAll();

        logger.info("{} villes retournées.", cities.size());
        return cities;
    }

    /**
     * Récupère une ville par son ID.
     *
     * @param id L'ID de la ville à récupérer.
     * @return L'enregistrement {@link CityDto} représentant la ville trouvée, ou {@code null} si aucune ville n'est trouvée.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDto findById(@PathVariable Long id) {
        logger.info("Requête reçue pour récupérer la ville avec l'ID : {}", id);

        CityDto city = cityService.findById(id);

        if (city != null) {
            logger.info("Ville trouvée : {}", city.name());
        } else {
            logger.warn("Aucune ville trouvée avec l'ID : {}", id);
        }

        return city;
    }

    /**
     * Crée une nouvelle ville.
     *
     * @param cityDto Les informations de la nouvelle ville à créer.
     * @return L'enregistrement {@link CityDto} représentant la ville créée.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto create(@RequestBody CityDto cityDto) {
        logger.info("Requête reçue pour créer une nouvelle ville : {}", cityDto);

        CityDto createdCity = cityService.create(cityDto);

        logger.info("Nouvelle ville créée avec l'ID : {}", createdCity.id());
        return createdCity;
    }

    /**
     * Met à jour une ville existante.
     *
     * @param cityDto Les nouvelles informations de la ville à mettre à jour.
     * @return L'enregistrement {@link CityDto} représentant la ville mise à jour, ou {@code null} si la mise à jour a échoué.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CityDto update(@RequestBody CityDto cityDto) {
        logger.info("Requête reçue pour mettre à jour la ville avec l'ID : {}", cityDto.id());

        CityDto updatedCity = cityService.update(cityDto);

        if (updatedCity != null) {
            logger.info("Ville mise à jour avec succès : {}", updatedCity);
        } else {
            logger.warn("Impossible de mettre à jour la ville avec l'ID : {}", cityDto.id());
        }

        return updatedCity;
    }

    /**
     * Supprime une ville par son ID.
     *
     * @param id L'ID de la ville à supprimer.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        logger.info("Requête reçue pour supprimer la ville avec l'ID : {}", id);

        cityService.delete(id);

        logger.info("Ville supprimée avec succès : {}", id);
    }
}
