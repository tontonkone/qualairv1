package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "zip")
public class Zip extends BaseEntity {

    private Integer code;

    @OneToMany(mappedBy = "zip")
    private Set<Address> addresses;

    @ManyToMany(mappedBy = "zips")
    private Set<City> cities;

    public void addAddress(Address address) {
        if (null != address) {
            address.setZip(this);
        }
    }

    public void removeAddress(Address address) {
        if (null != address) {
            address.setZip(null);
        }
    }

    public void addCity(City city) {
        if (null != city) {
            city.addZip(this);
        }
    }

    public void removeCity(City city) {
        if (null != city) {
            city.removeZip(this);
        }
    }

    @Override
    public String toString() {
        return "Zip{" +
                "code=" + code +
                "} " + super.toString();
    }
}
