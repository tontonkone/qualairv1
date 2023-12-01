package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "air_quality")
public class AirQuality extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private IndexType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "quality_index", nullable = false)
    private Index index;

    @ElementCollection
    @Column(name = "underindex")
    @CollectionTable(name = "air_quality_underindex_mapping")
    private Map<Pollutant, Index> underindexes = new HashMap<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    /**
     * Permet d'ajouter un sous-index pour un polluant donné
     * @param pollutant  le polluant associé au sous-index
     * @param underindex le sous-index à ajouter
     */
    public void addUnderIndex(Pollutant pollutant, Index underindex) {
        underindexes.put(pollutant, underindex);
    }

    /**
     * Permet de supprimer le sous-index d'un polluant donné.
     *
     * @param pollutant le polluant
     */
    public void removeUnderIndex(Pollutant pollutant) {
        underindexes.remove(pollutant);
    }


    @Override
    public String toString() {
        return "AirQuality{" +
                "type=" + type +
                ", index=" + index +
                ", underindexes=" + underindexes +
                "} " + super.toString();
    }
}