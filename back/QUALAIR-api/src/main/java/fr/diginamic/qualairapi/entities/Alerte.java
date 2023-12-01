package fr.diginamic.qualairapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Entité représentant une alerte.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    /**
     * Constructeur permettant d'initialiser une alerte avec un message.
     *
     * @param message Le message de l'alerte
     */
    public Alerte(String message) {
        this.message = message;
    }


    /**
     * Détermine si cette alerte est égale à un autre objet.
     *
     * @param o L'objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alerte alerte = (Alerte) o;
        return Objects.equals(id, alerte.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Alerte{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
