package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.RegionDto;
import fr.diginamic.qualairapi.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur de l'entité "Region" qui gère les opérations CRUD (Création, Lecture, Mise à jour, Suppression) pour les régions.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("region")
public class RegionController {
    private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

    private final RegionService regionService;

    /**
     * Récupère toutes les régions.
     *
     * @return Liste des objets {@link RegionDto} représentant toutes les régions.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RegionDto> findAll() {
        logger.info("Requête reçue pour récupérer toutes les régions.");

        List<RegionDto> regions = regionService.findAll();

        logger.info("{} régions retournées.", regions.size());
        return regions;
    }

    /**
     * Récupère une région par son ID.
     *
     * @param id L'ID de la région à récupérer.
     * @return L'objet {@link RegionDto} représentant la région trouvée, ou {@code null} si aucune région n'est trouvée.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegionDto findById(@PathVariable Long id) {
        logger.info("Requête reçue pour récupérer la région avec l'ID : {}", id);

        RegionDto region = regionService.findById(id);

        if (region != null) {
            logger.info("Région trouvée : {}", region.name());
        } else {
            logger.warn("Aucune région trouvée avec l'ID : {}", id);
        }

        return region;
    }

    /**
     * Crée une nouvelle région.
     *
     * @param regionDto Les informations de la nouvelle région à créer.
     * @return L'objet {@link RegionDto} représentant la région créée.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegionDto create(@RequestBody RegionDto regionDto) {
        logger.info("Requête reçue pour créer une nouvelle région : {}", regionDto);

        RegionDto createdRegion = regionService.create(regionDto);

        logger.info("Nouvelle région créée avec l'ID : {}", createdRegion.id());
        return createdRegion;
    }

    /**
     * Met à jour une région existante.
     *
     * @param regionDto Les nouvelles informations de la région à mettre à jour.
     * @return L'objet {@link RegionDto} représentant la région mise à jour, ou {@code null} si la mise à jour a échoué.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RegionDto update(@RequestBody RegionDto regionDto) {
        logger.info("Requête reçue pour mettre à jour la région avec l'ID : {}", regionDto.id());

        RegionDto updatedRegion = regionService.update(regionDto);

        if (updatedRegion != null) {
            logger.info("Région mise à jour avec succès : {}", updatedRegion);
        } else {
            logger.warn("Impossible de mettre à jour la région avec l'ID : {}", regionDto.id());
        }

        return updatedRegion;
    }

    /**
     * Supprime une région par son ID.
     *
     * @param id L'ID de la région à supprimer.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        logger.info("Requête reçue pour supprimer la région avec l'ID : {}", id);

        regionService.delete(id);

        logger.info("Département supprimé avec succès : {}", id);
    }
}

