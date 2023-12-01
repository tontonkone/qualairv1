import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { UserCenterComponent } from './components/header/user-center/user-center.component';
import {MapComponent} from "./components/map/map.component";
import {RouterLink} from "@angular/router";
import {NgbDropdownModule, NgbToastModule} from "@ng-bootstrap/ng-bootstrap";
import { ToastComponent } from './components/toast/toast.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';



@NgModule({
  declarations: [
    HeaderComponent,
    UserCenterComponent,
    MapComponent,
    ToastComponent,
    SearchComponent
  
  ],
  exports: [
    MapComponent,
    HeaderComponent,
    ToastComponent
  ],
  imports: [
    CommonModule,
    RouterLink,
    NgbDropdownModule,
    NgbToastModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class SharedModule { }
