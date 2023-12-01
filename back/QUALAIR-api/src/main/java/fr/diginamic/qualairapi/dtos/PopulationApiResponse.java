package fr.diginamic.qualairapi.dtos;

public record PopulationApiResponse(
        String code,
        String nom,
        Long population
) {
}
