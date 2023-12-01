package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entité représentant une ville.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class City extends BaseEntity {

    /** Code INSEE de la ville (unique) */
    @Column(unique = true)
    public Long inseeCode;

    /** Identifiant WAQI de la ville (unique) */
    @Column(unique = true)
    public Integer waqiId;

    /** Nom de la ville */
    private String name;

    /** Population de la ville */
    private Long population;

    /** Adresses associées à cette ville */
    @OneToMany(mappedBy="city")
    private Set<Address> addresses;

    /** Département associé à cette ville */
    @ManyToOne
    @JoinColumn(name = "id_department")
    @Setter(AccessLevel.NONE)
    private Department department;

    /** Codes postaux associés à cette ville */
    @ManyToMany
    @JoinTable(name = "city_zip_mapping",
            joinColumns = @JoinColumn(name = "id_city", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_zip", referencedColumnName = "id"))
    private Set<Zip> zips;

    /** Prévisions météorologiques associées à cette ville */
    @OneToMany(mappedBy = "city", orphanRemoval = true)
    private Set<WeatherForecast> weatherForecasts = new LinkedHashSet<>();

    /** Qualités de l'air associées à cette ville */
    @OneToMany(mappedBy = "city", orphanRemoval = true)
    private Set<AirQuality> airQualities = new LinkedHashSet<>();

    /** Ajoute une adresse à la ville */
    public void addAddress(Address address) {
        if (null != address) {
            address.setCity(this);
        }
    }

    /** Supprime une adresse de la ville */
    public void removeAddress(Address address) {
        if (null != address) {
            address.setCity(null);
        }
    }

    /** Définit le département associé à cette ville */
    public void setDepartment(Department department) {
        if (null != this.department) {
            this.department.getCities().remove(this);
        }
        if (null != department) {
            department.getCities().add(this);
        }
        this.department = department;
    }

    /** Ajoute un code postal à la ville */
    public void addZip(Zip zip) {
        if (null != zip) {
            zip.getCities().add(this);
            this.zips.add(zip);
        }
    }

    /** Supprime un code postal de la ville */
    public void removeZip(Zip zip) {
        if (null != zip) {
            zip.getCities().remove(this);
            this.zips.remove(zip);
        }
    }

    /** Ajoute une prévision météorologique à la ville */
    public void addWeatherForecast(WeatherForecast weatherForecast) {
        if (null != weatherForecast) {
            weatherForecast.setCity(this);
        }
    }

    /** Supprime une prévision météorologique de la ville */
    public void removeWeatherForecast(WeatherForecast weatherForecast) {
        if (null != weatherForecast) {
            weatherForecast.setCity(null);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de l'objet City.
     * @return La représentation sous forme de chaîne de l'objet City
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", department=" + department.getCode() +
                "} " + super.toString();
    }
}