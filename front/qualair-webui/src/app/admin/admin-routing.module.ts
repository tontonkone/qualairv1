import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './components/users/user-list/user-list.component';
import { authGuard } from '../user/guards/auth.guard';

const routes: Routes = [

  {
    path: '',
    component: UserListComponent,
    canActivate: [authGuard]
  },
  {
    path: 'list-users',
    component: UserListComponent,
    canActivate: [authGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
