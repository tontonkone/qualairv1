import {Component, Input} from '@angular/core';
import { ForumService } from 'src/app/shared/services/forum.service';


@Component({
  selector: 'app-forum-list',
  templateUrl: './forum-list.component.html',
  styleUrls: ['./forum-list.component.scss']
})
export class ForumListComponent {
  @Input() forums: any[]|null = null;

  constructor(private forumService: ForumService) {
    // this.forums = this.forumService.getAllForums();
  }
}
