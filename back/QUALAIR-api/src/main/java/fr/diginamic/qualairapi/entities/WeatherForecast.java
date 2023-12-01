package fr.diginamic.qualairapi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "weather_forecast")
public class WeatherForecast extends BaseEntity {

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "cloudcover")
    private Double cloudcover;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private City city;

    public void setCity(City city) {
        if (null != this.city) {
            this.city.getWeatherForecasts().remove(this);
        }
        if (null != city) {
            city.getWeatherForecasts().add(this);
        }
        this.city = city;
    }


    @Override
    public String toString() {
        return "WeatherForecast{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", cloudcover=" + cloudcover +
                ", city name=" + city.getName() +
                "} " + super.toString();
    }
}