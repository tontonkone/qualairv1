import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {Forum} from "../models/forum";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  constructor(
    private _http: HttpClient
  ) {
  }

  getAll() {
    return this._http.get<Forum[]>(`http://localhost:8080/threads`);
  }

  getById(forumId: number) {
    return this._http.get<Forum>(`http://localhost:8080/threads/${forumId}`);
  }

  addForum(forum: Forum) {
    return this._http.post<Forum>(`http://localhost:8080/threads`, forum);
  }
}
