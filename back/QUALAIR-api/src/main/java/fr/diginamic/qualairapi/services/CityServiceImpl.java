package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.ApiResponse;
import fr.diginamic.qualairapi.dtos.PopulationApiResponse;
import fr.diginamic.qualairapi.dtos.WeatherResponseApi;
import fr.diginamic.qualairapi.entities.*;
import fr.diginamic.qualairapi.repositories.CityRepository;
import fr.diginamic.qualairapi.services.mappers.CityMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Implémentation de l'interface {@link CityService} pour gérer les opérations liées aux villes.
 */
@RequiredArgsConstructor
@Getter
@Service
public class CityServiceImpl implements CityService {

    /**
     * URL pour récupérer les données de population d'une ville à partir d'une API externe.
     */
    private static final String populationApiUrl = "https://geo.api.gouv.fr/communes/{inseeCode}?fields=population";
        private static final String airApiUrl = "https://api.waqi.info/feed/{name}/?token=c4a8cb7a094794dbf13b097b24be568e812de3dc";

    /**
     * URL pour récupérer les données de prévision météorologique à partir d'une API externe.
     */
    private static final String meteoApiUrl = "https://wttr.in/{name}?format=j1";

    /**
     * Instance de Logger pour les journaux au sein de cette classe.
     */
    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    /**
     * Référentiel pour les opérations liées à la ville dans la base de données.
     */
    public final CityRepository repository;

    /**
     * Mapper pour la correspondance entre les DTO (objets de transfert de données) et les entités.
     */
    public final CityMapper mapper;

    /**
     * Récupère les données de population pour une ville donnée et met à jour la population de la ville.
     *
     * @param city La ville pour laquelle récupérer et mettre à jour la population.
     * @throws RuntimeException Si les données de population de la ville ne sont pas trouvées.
     */
    public void fetchCityPopulation(City city) {
        RestTemplate restTemplate = new RestTemplate();

        // Préparation de la requête pour récupérer les données de recensement à partir du code INSEE d'une ville.
        RequestEntity<Void> request = RequestEntity.get(populationApiUrl, city.getInseeCode())
                .build();
        ResponseEntity<PopulationApiResponse> response = restTemplate.exchange(request, PopulationApiResponse.class);

        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new RuntimeException("City population not found");
        }

        Optional<PopulationApiResponse> responseBody = Optional.ofNullable(response.getBody());

        PopulationApiResponse body = responseBody.orElseThrow(() -> new RuntimeException("City population not found"));

        // Modification de l'entité Ville avec les données récupérées
        city.setPopulation(body.population());
    }

    /**
     * Récupère les prévisions météorologiques pour une ville donnée et renvoie un objet WeatherForecast.
     *
     * @param city La ville pour laquelle récupérer les prévisions météorologiques.
     * @return Un objet WeatherForecast contenant les données météorologiques pour la ville.
     * @throws RuntimeException Si les données météorologiques de la ville ne sont pas trouvées ou sont incomplètes.
     */
    public WeatherForecast fetchWeatherForecast(City city) {
        RestTemplate restTemplate = new RestTemplate();

        // Préparation de la requête HTTP pour récupérer les données météo par rapport au nom de la ville
        RequestEntity<Void> request = RequestEntity.get(meteoApiUrl, city.getName())
                .build();

        // Exécution de la requête
        ResponseEntity<WeatherResponseApi> response = restTemplate.exchange(request, WeatherResponseApi.class);
        log.info("URl : {}", restTemplate.getUriTemplateHandler().expand(meteoApiUrl, city.getName()));

        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new RuntimeException("City not found");
        }
        Optional<WeatherResponseApi> responseApi = Optional.ofNullable(response.getBody());

        WeatherResponseApi body = responseApi.orElseThrow(() -> new RuntimeException("City not found"));

        log.info(String.valueOf(body));

        WeatherForecast weatherForecast = new WeatherForecast();

        // Préparation de la nouvelle entité à partir des données récupérée en base
        WeatherResponseApi.Condition currentCondition = body.currentCondition()
                .stream().
                findFirst().
                orElseThrow(()
                        -> new RuntimeException("No weather condition found for the city"));

        weatherForecast.setHumidity(Double.parseDouble(currentCondition.humidity()));
        weatherForecast.setCloudcover(Double.parseDouble(currentCondition.cloudcover()));
        weatherForecast.setTemperature(Double.parseDouble(currentCondition.temperature()));
        weatherForecast.setCity(city);

        return weatherForecast;
    }

    /**
     * Récupère les données de qualité de l'air pour une ville donnée et effectue le traitement nécessaire.
     *
     * @param city La ville pour laquelle récupérer les données de qualité de l'air.
     * @throws RuntimeException S'il y a un problème lors de la récupération ou du traitement des données de qualité de l'air.
     */
    public AirQuality fetchAirQuality(City city) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(airApiUrl, city.getName())
                .build();
        ResponseEntity<ApiResponse> response = restTemplate.exchange(request, ApiResponse.class);
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new RuntimeException("City not found");
        }
        Optional<ApiResponse> responseApi = Optional.ofNullable(response.getBody());
        ApiResponse body = responseApi.orElseThrow(() -> new RuntimeException("City not found"));

        log.info(String.valueOf(body));

        AirQuality airQuality = new AirQuality();
        ApiResponse.Data data = body.data();
        airQuality.setType(IndexType.PROJECTION);
        int aqi = data.aqi();
        if(aqi <= 50 ){
            airQuality.setIndex(Index.BON);
        } else if (aqi > 51 && aqi < 100 ) {
            airQuality.setIndex(Index.MOYEN);
        } else if (aqi > 101 && aqi < 150 ) {
            airQuality.setIndex(Index.DEGRADE);
        } else if (aqi > 151 && aqi < 200) {
            airQuality.setIndex(Index.MAUVAIS);
        }else if (aqi > 201 && aqi < 300 ){
            airQuality.setIndex(Index.TRES_MAUVAIS);
        }else{
            airQuality.setIndex(Index.EXTREMEMENT_MAUVAIS);
        }
        return airQuality;
    }

    public City findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("No city found with name " + name));
    }
}
