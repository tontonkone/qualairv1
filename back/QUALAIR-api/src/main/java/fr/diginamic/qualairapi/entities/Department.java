package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entité représentant un département.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "department")
public class Department extends BaseEntity {

    private String code;
    private String name;
    private int population;

    @OneToMany(mappedBy = "department")
    private Set<City> cities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_region")
    @Setter(AccessLevel.NONE)
    private Region region;

    public void addCity(City city) {
        if (null != city) {
            city.setDepartment(this);
        }
    }

    public void removeCity(City city) {
        if (null != city) {
            city.setDepartment(this);
        }
    }

    public void setRegion(Region region) {
        if (null != this.region) {
            this.region.getDepartments().remove(this);
        }
        if (null != region) {
            region.getDepartments().add(this);
        }
        this.region = region;
    }

    @Override
    public String toString() {
        return "Department{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", region='" + region.getName() + '\'' +
                "} " + super.toString();
    }
}
