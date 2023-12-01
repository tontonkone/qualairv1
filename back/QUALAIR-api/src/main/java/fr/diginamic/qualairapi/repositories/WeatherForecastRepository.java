package fr.diginamic.qualairapi.repositories;

import fr.diginamic.qualairapi.entities.City;
import fr.diginamic.qualairapi.entities.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {
    Optional<WeatherForecast> findFirstByCity_IdAndCreationDateOrderByCreationDateDesc(Long id, LocalDateTime creationDate);

    @Query("""
            select w from WeatherForecast w
            where w.city = :city
            order by w.creationDate DESC limit 1""")
    Optional<WeatherForecast> findByCityAndCreationDateOrderByCreationDateDesc(@Param("city") City city/*,
                                                                               @Param("creationDate") LocalDateTime creationDate*/);


}