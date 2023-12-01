import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForumRoutingModule } from './forum-routing.module';
import { ForumSpacesComponent } from './components/forum-spaces/forum-spaces.component';
import { ForumListComponent } from './components/forum-spaces/forum-list/forum-list.component';
import { ForumItemComponent } from './components/forum-spaces/forum-item/forum-item.component';
import { ForumFormComponent } from './components/forum-form/forum-form.component';
import { ForumsComponent } from './components/forums/forums.component';
import { MessageListComponent } from './components/forums/message-list/message-list.component';
import { MessageItemComponent } from './components/forums/message-item/message-item.component';
import { MessageFormComponent } from './components/forums/message-form/message-form.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ForumSpacesComponent,
    ForumListComponent,
    ForumItemComponent,
    ForumFormComponent,
    ForumsComponent,
    MessageListComponent,
    MessageItemComponent,
    MessageFormComponent
  ],
  imports: [
    CommonModule,
    ForumRoutingModule,
    FormsModule
  ]
})
export class ForumModule { }
