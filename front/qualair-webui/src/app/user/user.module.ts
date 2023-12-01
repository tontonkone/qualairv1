import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { LoginFormComponent } from './components/login/login-form/login-form.component';
import { LoginComponent } from './components/login/login.component';
import { BookmarkDetailsComponent } from './components/profil/bookmark-details/bookmark-details.component';
import { BookmarkItemComponent } from './components/profil/bookmark-item/bookmark-item.component';
import { BookmarkListComponent } from './components/profil/bookmark-list/bookmark-list.component';
import { ProfilFromComponent } from './components/profil/profil-from/profil-from.component';
import { ProfilComponent } from './components/profil/profil.component';
import { RegisterFormComponent } from './components/register/register-form/register-form.component';
import { RegisterComponent } from './components/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import {RouterModule} from "@angular/router";
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    LoginComponent,
    LoginFormComponent,
    RegisterComponent,
    RegisterFormComponent,
    ProfilComponent,
    ProfilFromComponent,
    BookmarkListComponent,
    BookmarkItemComponent,
    BookmarkDetailsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    SharedModule
  ],
  exports: [
    LoginComponent,
    RegisterComponent,
    ProfilComponent
  ]
})
export class UserModule { }
