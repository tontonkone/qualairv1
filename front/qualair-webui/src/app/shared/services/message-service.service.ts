import { Injectable } from '@angular/core';
import { Message } from '../models/message';
import {BehaviorSubject} from "rxjs";
import {ForumService} from "./forum.service";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class MessageService {
  constructor(private _http: HttpClient) { }

  getAllByForum(forumId: string) {
    return this._http.get<Message[]>(`http://localhost:8080/posts/thread/${forumId}`);
  }

  // Fonction pour ajouter un nouveau message
  addMessage(message: Message) { // Générez un ID unique
    return this._http.post<Message>(`http://localhost:8080/posts`, message);
  }
}
