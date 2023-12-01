import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { GeojsonService } from '../../services/geojson.service';
import { GeoJSONCollection } from './models';
import { GeoJsonObject } from 'geojson';
import { SearchService } from '../../services/search.service';
import { AirQualityService } from '../../services/air-quality.service';
import { WeatherResponse } from '../../models/air-quality';

/**
 * Composant Angular pour afficher une carte Leaflet avec des données GeoJSON.
 */
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  private map!: L.Map;
  private datadb!: GeoJSONCollection;
  private airQualityData?: WeatherResponse;
  private geojsonLayer!: L.GeoJSON;

  private osmLayer!: L.TileLayer;
  private esriLayer!: L.TileLayer;
  private openTopoLayer!: L.TileLayer;

  private baseMaps: any = {};

  /**
   * Constructeur du composant.
   * @param _geojsonService Service pour récupérer les données GeoJSON.
   * @param _searchService Service pour effectuer des recherches.
   */
  constructor(private _geojsonService: GeojsonService,
    private _airQualityService: AirQualityService,
    private _searchService: SearchService) { }

  /**
   * Méthode du cycle de vie Angular appelée après la construction du composant.
   * Initialise la carte avec des tuiles OSM et les données GeoJSON.
   */
  ngOnInit(): void {
    this._geojsonService.getGeojsonData().subscribe(data => {
      this.datadb = data;
      this.initializeMap();
    });

    this._searchService.search$.subscribe(data => {
      this.updateGeoJSONLayer(data);
    });
  }

  /**
   * Initialise la carte Leaflet avec des tuiles OSM et les données GeoJSON.
   */
  private initializeMap(): void {
    this.osmLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    });

    this.esriLayer = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
      attribution: '© Esri'
    });

    this.openTopoLayer = L.tileLayer('https://{s}.tile.opentopomap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenTopoMap contributors'
    });

    this.baseMaps = {
      OSM: this.osmLayer,
      'Esri world': this.esriLayer,
      'open map ': this.openTopoLayer
    };
    this.map = L.map('map', {
      center: [48.86131, 2.34054],
      zoom: 8,
      layers: [this.osmLayer]
    });

    this.addLayersControl();
    this.updateGeoJSONLayer('');
  }

  /**
   * Ajoute le contrôle des couches à la carte.
   */
  private addLayersControl(): void {
    L.control.layers(this.baseMaps).addTo(this.map);
  }

  /**
   * Met à jour la couche GeoJSON en fonction du terme de recherche.
   * @param searchTerm Terme de recherche.
   */
  private updateGeoJSONLayer(searchTerm: string): void {
    if (!this.datadb || !this.datadb.features) {
      return;
    }

    // Filtrer les fonctionnalités en fonction du terme de recherche
    const filteredFeatures = this.datadb.features.filter((feature) => {
      return feature.properties.nom.toLowerCase().includes(searchTerm.toLowerCase());
    });


    // Créez la couche GeoJSON avec les fonctionnalités filtrées
    const newGeojsonLayer = L.geoJSON(filteredFeatures as any, {
      style: (feature) => {
        return {
          color: '#096b6b',
          fillColor: 'gray'
        };
      },
      onEachFeature: (feature, layer) => {
        let styleClass = ""
        layer.on('click', (event) => {
          const clickedLayer = event.target;
          
          let value = clickedLayer._latlngs[0][0]
          if (value.length > 1) {
            value = value[0]
          }
          const geocodeString = Object.values(value).join(";")
          const encoding = encodeURI(geocodeString)

          this._airQualityService.findByLatLng(encoding).subscribe(data => {
            this.airQualityData = data;

            let color =""
            if (this.airQualityData.data.aqi <= 50) {
              styleClass = '🟢';
              color = "#096"
            } else if (this.airQualityData.data.aqi > 51 && this.airQualityData.data.aqi < 100) {
              styleClass = '🟠';
              color = "#f93"
            } else if (this.airQualityData.data.aqi > 101 && this.airQualityData.data.aqi < 150) {
              styleClass = '🟡';
              color = "#ffde33"
            } else if (this.airQualityData.data.aqi > 151 && this.airQualityData.data.aqi < 200) {
              styleClass = '🔴';
              color = "#c03"
            } else if (this.airQualityData.data.aqi > 201 && this.airQualityData.data.aqi < 300) {
              styleClass = '🟣';
              color = "#609"
            } else {
              styleClass = '🟤';
              color = "#7e0023"
            }

            clickedLayer.setStyle({
              fillColor: color
            });
            layer.setPopupContent(this.generatePopupContent(feature, styleClass))
          })
          
        });
        layer.bindPopup(this.spinnerPopup())

        if (searchTerm && searchTerm.length > 3) {
          this._airQualityService.findByKeyword(searchTerm).subscribe(data => console.log(data))
        }

      }
    });

    // Supprimez l'ancienne couche GeoJSON de la carte si elle existe
    if (this.geojsonLayer) {
      this.map.removeLayer(this.geojsonLayer);
    }

    // Ajoutez la nouvelle couche GeoJSON à la carte
    newGeojsonLayer.addTo(this.map);

    // Mettez à jour la référence à la nouvelle couche GeoJSON
    this.geojsonLayer = newGeojsonLayer;
  }

  private spinnerPopup(){
    return `
    <div class="spinner-border" role="status">
  <span class="sr-only"></span>
</div>`
  }
  private generatePopupContent(feature: any, styleClass: string): string {
    return `
        <div>
          <h5 >Air Quality Information</h5>
          <div>
            <p  ><strong>Dept:</strong> ${feature.properties.nom}</p>
            <p ><strong>Air Quality:</strong> ${this.airQualityData?.data.aqi} ${styleClass}</p>

            <div>
              <ul class="list-group">
                <li class="list-group-item">
                  <strong>PM2.5:</strong> ${this.airQualityData?.data.iaqi.pm25?.v ?? "🤷‍♂️"} µg/m³
                </li>
                <li class="list-group-item">
                  <strong>PM10:</strong> ${this.airQualityData?.data.iaqi.pm10?.v ?? "🤷‍♂️"} µg/m³
                </li>
                <li class="list-group-item">
                  <strong>Ozone (O3):</strong> ${this.airQualityData?.data.iaqi.o3?.v ?? "🤷‍♂️"} µg/m³
                </li>
                <li class="list-group-item">
                  <strong>Nitrogen Dioxide (NO2):</strong> ${this.airQualityData?.data.iaqi.no2?.v ?? "🤷‍♂️"} µg/m³
                </li>
                <!-- Ajoutez d'autres informations ici -->
              </ul>
            </div>
          </div>
        </div>
  `;
  }
}
