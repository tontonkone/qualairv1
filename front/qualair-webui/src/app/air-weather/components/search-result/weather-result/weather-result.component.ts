import {Component, Input, OnInit} from '@angular/core';
import {WeatherForecastService} from "../../../../shared/services/weather-forecast.service";
import {WeatherForecast} from "../../../../shared/models/weather-forecast";
import {Observable, tap} from "rxjs";

@Component({
    selector: 'app-weather-result',
    templateUrl: './weather-result.component.html',
    styleUrls: ['./weather-result.component.scss']
})
export class WeatherResultComponent implements OnInit {
    @Input() city!: string;
    weatherForecast!: Observable<WeatherForecast>;

    constructor(private weatherForecastService: WeatherForecastService) {
    }

    ngOnInit(): void {
        if (this.city) {
          this.weatherForecast = this.weatherForecastService.findCurrentByCityName(this.city)
            .pipe(
              tap(() => console.log("test"))
            );
        }
    }
}
