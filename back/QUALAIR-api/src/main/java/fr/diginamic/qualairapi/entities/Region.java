package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
public class Region extends BaseEntity {

    private String name;

    private Integer population;

    @OneToMany(mappedBy = "region")
    private Set<Department> departments = new HashSet<>();

    public void addDepartment(Department department) {
        if (null != department) {
            department.setRegion(this);
        }
    }

    public void removeDepartment(Department department) {
        if (null != department) {
            department.setRegion(null);
        }
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", population=" + population +
                "} " + super.toString();
    }
}
