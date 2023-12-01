import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/shared/models/message';
import { MessageService } from 'src/app/shared/services/message-service.service';
import {Forum} from "../../../shared/models/forum";
import {ActivatedRoute} from "@angular/router";
import {ForumService} from "../../../shared/services/forum.service";
import {Observable} from "rxjs";


@Component({
  selector: 'app-forums',
  templateUrl: './forums.component.html',
  styleUrls: ['./forums.component.scss']
})
export class ForumsComponent  {
  forum$!: Observable<Forum>;
  messages$!: Observable<Message[]>;
  private readonly forumId!: string;

  constructor(
    private forumService: ForumService,
    private messageService: MessageService,
    private route: ActivatedRoute,
  ) {
    const forumId = this.route.snapshot.paramMap.get("id");
    if (forumId) {
      this.forumId = forumId;
      this.forum$ = this.forumService.getById(Number.parseInt(forumId));
      this.messages$ = this.messageService.getAllByForum(forumId);
    }
  }

  //ngOnInit(): void {}

  onMessageAdded() {
    this.messages$ = this.messageService.getAllByForum(this.forumId);
  }
}
