import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForumsComponent } from './components/forums/forums.component';
import {ForumSpacesComponent} from "./components/forum-spaces/forum-spaces.component";
import {ForumFormComponent} from "./components/forum-form/forum-form.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: ForumSpacesComponent
  },
  {
    path: 'form',
    component: ForumFormComponent
  },
  {
    path: ':id',
    component: ForumsComponent
  }
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ForumRoutingModule { }
