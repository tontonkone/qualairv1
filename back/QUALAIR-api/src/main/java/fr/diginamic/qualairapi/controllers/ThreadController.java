package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.ThreadDto;
import fr.diginamic.qualairapi.services.ThreadService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur de l'entité "Thread" qui gère les opérations CRUD (Création, Lecture, Mise à jour, Suppression) pour les threads.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("threads")
public class ThreadController {
    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    private final ThreadService threadService;

    /**
     * Récupère tous les threads.
     *
     * @return Liste des enregistrements {@link ThreadDto} représentant tous les threads.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ThreadDto> findAll() {
        logger.info("Requête reçue pour récupérer tous les threads.");

        List<ThreadDto> threads = threadService.findAll();

        logger.info("{} threads retournés.", threads.size());
        return threads;
    }

    /**
     * Récupère un thread par son ID.
     *
     * @param id L'ID du thread à récupérer.
     * @return L'enregistrement {@link ThreadDto} représentant le thread trouvé, ou {@code null} si aucun thread n'est trouvé.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThreadDto findById(@PathVariable Long id) {
        logger.info("Requête reçue pour récupérer le thread avec l'ID : {}", id);

        ThreadDto thread = threadService.findById(id);

        if (thread != null) {
            logger.info("Thread trouvé : {}", thread.title());
        } else {
            logger.warn("Aucun thread trouvé avec l'ID : {}", id);
        }

        return thread;
    }

    /**
     * Crée un nouveau thread.
     *
     * @param threadDto Les informations du nouveau thread à créer.
     * @return L'enregistrement {@link ThreadDto} représentant le thread créé.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ThreadDto create(@RequestBody ThreadDto threadDto) {
        logger.info("Requête reçue pour créer un nouveau thread : {}", threadDto);

        ThreadDto createdThread = threadService.create(threadDto);

        logger.info("Nouveau thread créé avec l'ID : {}", createdThread.id());
        return createdThread;
    }

    /**
     * Met à jour un thread existant.
     *
     * @param threadDto Les nouvelles informations du thread à mettre à jour.
     * @return L'enregistrement {@link ThreadDto} représentant le thread mis à jour, ou {@code null} si la mise à jour a échoué.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ThreadDto update(@RequestBody ThreadDto threadDto) {
        logger.info("Requête reçue pour mettre à jour le thread avec l'ID : {}", threadDto.id());

        ThreadDto updatedThread = threadService.update(threadDto);

        if (updatedThread != null) {
            logger.info("Thread mis à jour avec succès : {}", updatedThread);
        } else {
            logger.warn("Impossible de mettre à jour le thread avec l'ID : {}", threadDto.id());
        }

        return updatedThread;
    }

    /**
     * Supprime un thread par son ID.
     *
     * @param id L'ID du thread à supprimer.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        logger.info("Requête reçue pour supprimer le thread avec l'ID : {}", id);

        threadService.delete(id);

        logger.info("Thread supprimé avec succès : {}", id);
    }
}
