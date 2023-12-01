package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.DepartmentDto;
import fr.diginamic.qualairapi.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur de l'entité "Department" qui gère les opérations CRUD (Création, Lecture, Mise à jour, Suppression) pour les départements.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("department")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    /**
     * Récupère tous les départements.
     *
     * @return Liste des objets {@link DepartmentDto} représentant tous les départements.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentDto> findAll() {
        logger.info("Requête reçue pour récupérer tous les départements.");

        List<DepartmentDto> departments = departmentService.findAll();

        logger.info("{} départements retournés.", departments.size());
        return departments;
    }

    /**
     * Récupère un département par son ID.
     *
     * @param id L'ID du département à récupérer.
     * @return L'objet {@link DepartmentDto} représentant le département trouvé, ou {@code null} si aucun département n'est trouvé.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDto findById(@PathVariable Long id) {
        logger.info("Requête reçue pour récupérer le département avec l'ID : {}", id);

        DepartmentDto department = departmentService.findById(id);

        if (department != null) {
            logger.info("Département trouvé : {}", department.name());
        } else {
            logger.warn("Aucun département trouvé avec l'ID : {}", id);
        }

        return department;
    }

    /**
     * Crée un nouveau département.
     *
     * @param departmentDto Les informations du nouveau département à créer.
     * @return L'objet {@link DepartmentDto} représentant le département créé.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto create(@RequestBody DepartmentDto departmentDto) {
        logger.info("Requête reçue pour créer un nouveau département : {}", departmentDto);

        DepartmentDto createdDepartment = departmentService.create(departmentDto);

        logger.info("Nouveau département créé avec l'ID : {}", createdDepartment.id());
        return createdDepartment;
    }

    /**
     * Met à jour un département existant.
     *
     * @param departmentDto Les nouvelles informations du département à mettre à jour.
     * @return L'objet {@link DepartmentDto} représentant le département mis à jour, ou {@code null} si la mise à jour a échoué.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDto update(@RequestBody DepartmentDto departmentDto) {
        logger.info("Requête reçue pour mettre à jour le département avec l'ID : {}", departmentDto.id());

        DepartmentDto updatedDepartment = departmentService.update(departmentDto);

        if (updatedDepartment != null) {
            logger.info("Département mis à jour avec succès : {}", updatedDepartment);
        } else {
            logger.warn("Impossible de mettre à jour le département avec l'ID : {}", departmentDto.id());
        }

        return updatedDepartment;
    }

    /**
     * Supprime un département par son ID.
     *
     * @param id L'ID du département à supprimer.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        logger.info("Requête reçue pour supprimer le département avec l'ID : {}", id);

        departmentService.delete(id);

        logger.info("Département supprimé avec succès : {}", id);
    }
}
