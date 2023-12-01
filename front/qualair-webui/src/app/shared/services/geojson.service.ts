import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { GeoJSONCollection } from '../components/map/models';

@Injectable({
  providedIn: 'root'
})
export class GeojsonService {

  private clickSubject = new Subject<string>()

  constructor(private _http: HttpClient) {  }

  getGeojsonData(): Observable<GeoJSONCollection> {
    const url = 'https://france-geojson.gregoiredavid.fr/repo/departements.geojson';
    return this._http.get<GeoJSONCollection>(url);
  }

  emitClickEvent(color: string): void {
    this.clickSubject.next(color);
  }

  getClickEvent(): Observable<string> {
    return this.clickSubject.asObservable();
  }
}
