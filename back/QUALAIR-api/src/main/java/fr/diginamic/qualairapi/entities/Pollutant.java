package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entité représentant un polluant.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pollutant")
public class Pollutant extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true)
    private Integer code;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "pollutant")
    private Set<Bookmark> bookmarks = new LinkedHashSet<>();


    @Override
    public String toString() {
        return "Pollutant{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", bookmarks=" + bookmarks +
                "} " + super.toString();
    }
}