package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Entité représentant une adresse.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address extends BaseEntity {

    private String ligne1;

    private String ligne2;

    @OneToMany(mappedBy = "address")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "id_city")
    @Setter(AccessLevel.NONE)
    private City city;

    @ManyToOne
    @JoinColumn(name = "id_zip")
    @Setter(AccessLevel.NONE)
    private Zip zip;

    /**
     * Définit la ville associée à cette adresse.
     * Met à jour la relation bidirectionnelle entre la ville et l'adresse.
     *
     * @param city La ville à associer
     */
    public void setCity(City city) {
        if (null != this.city) {
            this.city.getAddresses().remove(this);
        }
        if (null != city) {
            city.getAddresses().add(this);
        }
        this.city = city;
    }

    /**
     * Définit le code postal associé à cette adresse.
     * Met à jour la relation bidirectionnelle entre le code postal et l'adresse.
     *
     * @param zip Le code postal à associer
     */
    public void setZip(Zip zip) {
        if (null != this.zip) {
            this.zip.getAddresses().remove(this);
        }
        if (null != zip) {
            zip.getAddresses().add(this);
        }
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "ligne1='" + ligne1 + '\'' +
                ", ligne2='" + ligne2 + '\'' +
                ", zip code=" + zip.getCode() +
                ", city name='" + city.getName() + '\'' +
                "} " + super.toString();
    }
}
