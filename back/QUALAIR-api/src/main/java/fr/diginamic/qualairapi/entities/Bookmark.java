package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private BookmarkCategory category;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "pollutant_id")
    private Pollutant pollutant;

    /**
     * Définit le polluant associé à ce signet.
     * Met à jour la relation bidirectionnelle entre le polluant et le signet.
     *
     * @param pollutant Le polluant à associer
     */
    public void setPollutant(Pollutant pollutant) {
        if (this.pollutant != null) {
            this.pollutant.getBookmarks().remove(this);
        }
        if (pollutant != null) {
            pollutant.getBookmarks().add(this);
        }
        this.pollutant = pollutant;
    }
}