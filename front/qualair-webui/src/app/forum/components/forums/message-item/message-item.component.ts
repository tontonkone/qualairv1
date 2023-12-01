import {Component, Input} from '@angular/core';
import {Message} from "../../../../shared/models/message";

@Component({
  selector: 'app-message-item',
  templateUrl: './message-item.component.html',
  styleUrls: ['./message-item.component.scss']
})
export class MessageItemComponent {
  @Input() message!: Message;
}
