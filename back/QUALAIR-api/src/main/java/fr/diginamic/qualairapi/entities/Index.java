package fr.diginamic.qualairapi.entities;

import lombok.Getter;

/**
 * Enumération représentant les indices de qualité de l'air.
 */
@Getter
public enum Index {
    BON(1, "", "bon"),
    MOYEN(2, "", "moyen"),
    DEGRADE(3, "", "dégradé"),
    MAUVAIS(4, "", "mauvais"),
    TRES_MAUVAIS(5, "", "très mauvais"),
    EXTREMEMENT_MAUVAIS(6, "", "extrêmement mauvais");

    private final int value;
    private final String color;
    private final String label;

    /**
     * Constructeur de l'indice avec ses propriétés.
     *
     * @param value La valeur numérique de l'indice
     * @param color La couleur associée à l'indice
     * @param label L'étiquette de l'indice
     */
    Index(int value, String color, String label) {
        this.value = value;
        this.color = color;
        this.label = label;
    }
}
