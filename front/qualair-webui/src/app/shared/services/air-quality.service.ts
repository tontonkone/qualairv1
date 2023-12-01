import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject, first, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AirQuality, WeatherResponse } from '../models/air-quality';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AirQualityService {

  private _token = environment.TOKEN
  private _airQualities$: BehaviorSubject<AirQuality[]> = new BehaviorSubject<AirQuality[]>([]);

  private _airSubject = new Subject<WeatherResponse>();
  subjectAir$ = this._airSubject.asObservable()

  constructor(private _http: HttpClient) {}

  refreshData() {
    this._http.get<AirQuality[]>(`http://localhost:8080/air-qualities`)
      .pipe(
        first()
      )
      .subscribe(airQualities => this._airQualities$.next(airQualities));
  }

  findByKeyword(keyword: string){
    return this._http.get<WeatherResponse>(`https://api.waqi.info/search/?keyword=${keyword}&token=${this._token}`)
  }

  findByLatLng(latlng: string) {
    return this._http.get<WeatherResponse>(`https://api.waqi.info/feed/geo:${latlng}/?token=${this._token}`)

  }


  findAll() {
    this.refreshData();
    return this._airQualities$.asObservable();
  }

  findById(id: number) {
    return this._http.get<AirQuality>(`http://localhost:8080/air-qualities/${id}`);
  }

  create(airQuality: Partial<AirQuality>) {
    this._http.post(`http://localhost:8080/air-qualities`, airQuality)
      .subscribe(() => this.refreshData());
  }

  update(airQuality: Partial<AirQuality>) {
    this._http.patch(`http://localhost:8080/air-qualities`, airQuality)
      .subscribe(() => this.refreshData());
  }

  delete(id: number) {
    this._http.delete(`http://localhost:8080/air-qualities/${id}`)
      .subscribe(() => this.refreshData());
  }

  findByCityName(cityName: string) {
    return this._http.get<AirQuality>(`http://localhost:8080/air-qualities`, { params: { city: cityName } });
  }
}
