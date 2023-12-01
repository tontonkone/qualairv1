import { Component } from '@angular/core';
import {Message} from "../../../shared/models/message";
import {ForumService} from "../../../shared/services/forum.service";
import {Forum} from "../../../shared/models/forum";
import {Observable} from "rxjs";

@Component({
  selector: 'app-forum-spaces',
  templateUrl: './forum-spaces.component.html',
  styleUrls: ['./forum-spaces.component.scss']
})
export class ForumSpacesComponent {
  forums!: Observable<Forum[]>;

  constructor(private forumService: ForumService) {
    this.forums = this.forumService.getAll();
  }
}
