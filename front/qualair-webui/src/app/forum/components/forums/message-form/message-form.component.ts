import {ApplicationRef, Component, EventEmitter, Input, Output} from '@angular/core';
import {MessageService} from "../../../../shared/services/message-service.service";
import {AuthService} from "../../../../shared/services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.scss']
})
export class MessageFormComponent {
  @Input() forumId!: number;
  messageContent: string = "";
  @Output() messageAdded: EventEmitter<null> = new EventEmitter<null>();
  constructor(
    private _messageService: MessageService,
    private _authService: AuthService,
    private _router: Router
  ) { }

  addMessage() {
    this._authService.userDetails$.subscribe(user => {
      if (user) {
        this._messageService.addMessage({
          author: user,
          content: this.messageContent,
          threadId: this.forumId,
          reactions: [],
        }).subscribe(() => this.messageAdded.next(null));
        this.messageContent = "";
      } else {
        alert("Vous devez être connecté");
      }
    })
  }
}
