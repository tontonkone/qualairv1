import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {authGuard} from "./user/guards/auth.guard";
import {LoginComponent} from "./user/components/login/login.component";
import {RegisterComponent} from "./user/components/register/register.component";
import {ProfilComponent} from "./user/components/profil/profil.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/air-weather/search'},
  { path: 'admin', canActivate: [ authGuard ], loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: 'air-weather', loadChildren: () => import('./air-weather/air-weather.module').then(m => m.AirWeatherModule) },
  { path: 'forums', loadChildren: () => import('./forum/forum.module').then(m => m.ForumModule) },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profil', component: ProfilComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
