import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminNavComponent } from './components/admin-nav/admin-nav.component';
import { UsersComponent } from './components/users/users.component';
import { UserListComponent } from './components/users/user-list/user-list.component';
import { UserItemComponent } from './components/users/user-item/user-item.component';
import { UserDetailsComponent } from './components/users/user-details/user-details.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { NotificationItemComponent } from './components/notifications/notification-item/notification-item.component';
import { ForumsComponent } from './components/forums/forums.component';
import { ForumDetailsComponent } from './components/forums/forum-details/forum-details.component';
import { NotificationListComponent } from "./components/notifications/notification-list/notification-list.component";
import { ForumListComponent } from "./components/forums/forum-list/forum-list.component";
import {AdminForumItemComponent} from "./components/forums/forum-item/admin-forum-item.component";



@NgModule({
  declarations: [
    AdminNavComponent,
    UsersComponent,
    UserListComponent,
    UserItemComponent,
    UserDetailsComponent,
    NotificationsComponent,
    NotificationListComponent,
    NotificationItemComponent,
    ForumsComponent,
    ForumListComponent,
    AdminForumItemComponent,
    ForumDetailsComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
