import {Injectable} from '@angular/core';
import {WeatherForecast} from "../models/weather-forecast";
import {HttpClient} from "@angular/common/http";


@Injectable({
    providedIn: 'root'
})
export class WeatherForecastService {

    constructor(private http: HttpClient) {
    }

    findCurrentByCityName(cityName: string) {
        return this.http.get<WeatherForecast>(`http://localhost:8080/weather/current`, {params: {city: cityName}});
    }
}
