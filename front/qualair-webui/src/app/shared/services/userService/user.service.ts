import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IUser} from "../../models/userModel/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(
    private _httpClient: HttpClient,
  ) {
  }

  getByUsername(username: string) {
    return this._httpClient.get<IUser>('http://localhost:8080/users/username/', {
      params: {
        username,
      }
    });
  }
}
