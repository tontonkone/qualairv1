import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AirWeatherRoutingModule } from './air-weather-routing.module';
import { SearchComponent } from './components/search/search.component';
import { SearchFormComponent } from './components/search/search-form/search-form.component';
import { SearchResultComponent } from './components/search-result/search-result.component';
import { WeatherResultComponent } from './components/search-result/weather-result/weather-result.component';
import { AirResultComponent } from './components/search-result/air-result/air-result.component';
import { WeatherDetailedResultComponent } from './components/search-result/weather-detailed-result/weather-detailed-result.component';
import { AirDetailedResultComponent } from './components/search-result/air-detailed-result/air-detailed-result.component';
import { HistorySearchComponent } from './components/history-search/history-search.component';
import { HistorySearchFormComponent } from './components/history-search/history-search-form/history-search-form.component';
import { HistoryResultComponent } from './components/history-result/history-result.component';
import { HistoryGraphComponent } from './components/history-result/history-graph/history-graph.component';
import { ReactiveFormsModule } from "@angular/forms";
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    SearchComponent,
    SearchFormComponent,
    SearchResultComponent,
    WeatherResultComponent,
    AirResultComponent,
    WeatherDetailedResultComponent,
    AirDetailedResultComponent,
    HistorySearchComponent,
    HistorySearchFormComponent,
    HistoryResultComponent,
    HistoryGraphComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AirWeatherRoutingModule,
    SharedModule
  ]
})
export class AirWeatherModule { }
