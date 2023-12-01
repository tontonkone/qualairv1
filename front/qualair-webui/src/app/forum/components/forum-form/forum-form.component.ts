import { Component, OnInit } from '@angular/core';
import { Forum } from 'src/app/shared/models/forum';
import { Thread } from 'src/app/shared/models/thread';
import { ForumService } from 'src/app/shared/services/forum.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-forum-form',
  templateUrl: './forum-form.component.html',
  styleUrls: ['./forum-form.component.scss']
})
export class ForumFormComponent {
  newForum: Forum = { id: 0, title: '', messages: [] };

  constructor(
    private forumService: ForumService, // Injection du service ForumService
    private router: Router
  ) { }

  createForum(): void {
    this.forumService.addForum(this.newForum)
      .subscribe(() => this.router.navigate(['/forums']));
  }
}
