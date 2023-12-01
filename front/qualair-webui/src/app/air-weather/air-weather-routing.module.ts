import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SearchComponent} from "./components/search/search.component";
import {SearchResultComponent} from "./components/search-result/search-result.component";
import {HistorySearchComponent} from "./components/history-search/history-search.component";
import {HistoryResultComponent} from "./components/history-result/history-result.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'search'},
  {path: 'search', component: SearchComponent},
  {path: 'result', component: SearchResultComponent},
  {path: 'history-search', component: HistorySearchComponent},
  {path: 'history-result', component: HistoryResultComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AirWeatherRoutingModule { }
