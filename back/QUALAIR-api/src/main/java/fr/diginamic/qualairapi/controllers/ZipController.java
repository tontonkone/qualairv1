package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.ZipDto;
import fr.diginamic.qualairapi.services.ZipService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur de l'entité "Zip" qui gère les opérations CRUD (Création, Lecture, Mise à jour, Suppression) pour les codes postaux.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("zip")
public class ZipController {
    private static final Logger logger = LoggerFactory.getLogger(ZipController.class);

    private final ZipService zipService;

    /**
     * Récupère tous les codes postaux.
     *
     * @return Liste des enregistrements {@link ZipDto} représentant tous les codes postaux.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ZipDto> findAll() {
        logger.info("Requête reçue pour récupérer tous les codes postaux.");

        List<ZipDto> zips = zipService.findAll();

        logger.info("{} codes postaux retournés.", zips.size());
        return zips;
    }

    /**
     * Récupère un code postal par son ID.
     *
     * @param id L'ID du code postal à récupérer.
     * @return L'enregistrement {@link ZipDto} représentant le code postal trouvé, ou {@code null} si aucun code postal n'est trouvé.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ZipDto findById(@PathVariable Long id) {
        logger.info("Requête reçue pour récupérer le code postal avec l'ID : {}", id);

        ZipDto zip = zipService.findById(id);

        if (zip != null) {
            logger.info("Code postal trouvé : {}", zip.code());
        } else {
            logger.warn("Aucun code postal trouvé avec l'ID : {}", id);
        }

        return zip;
    }

    /**
     * Crée un nouveau code postal.
     *
     * @param zipDto Les informations du nouveau code postal à créer.
     * @return L'enregistrement {@link ZipDto} représentant le code postal créé.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZipDto create(@RequestBody ZipDto zipDto) {
        logger.info("Requête reçue pour créer un nouveau code postal : {}", zipDto);

        ZipDto createdZip = zipService.create(zipDto);

        logger.info("Nouveau code postal créé avec l'ID : {}", createdZip.id());
        return createdZip;
    }

    /**
     * Met à jour un code postal existant.
     *
     * @param zipDto Les nouvelles informations du code postal à mettre à jour.
     * @return L'enregistrement {@link ZipDto} représentant le code postal mis à jour, ou {@code null} si la mise à jour a échoué.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ZipDto update(@RequestBody ZipDto zipDto) {
        logger.info("Requête reçue pour mettre à jour le code postal avec l'ID : {}", zipDto.id());

        ZipDto updatedZip = zipService.update(zipDto);

        if (updatedZip != null) {
            logger.info("Code postal mis à jour avec succès : {}", updatedZip);
        } else {
            logger.warn("Impossible de mettre à jour le code postal avec l'ID : {}", zipDto.id());
        }

        return updatedZip;
    }

    /**
     * Supprime un code postal par son ID.
     *
     * @param id L'ID du code postal à supprimer.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        logger.info("Requête reçue pour supprimer le code postal avec l'ID : {}", id);

        zipService.delete(id);

        logger.info("Code postal supprimé avec succès : {}", id);
    }
}
