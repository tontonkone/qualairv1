import {Component, Input} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ForumService } from 'src/app/shared/services/forum.service';
import {Forum} from "../../../../shared/models/forum";

@Component({
  selector: 'app-forum-item',
  templateUrl: './forum-item.component.html',
  styleUrls: ['./forum-item.component.scss']
})
export class ForumItemComponent {
  @Input() forum!: Forum;

  constructor() {
  }
}
