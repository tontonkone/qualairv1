import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { LoginResponse } from 'src/app/shared/models/auth/login-response.payload';
import { IUser } from 'src/app/shared/models/userModel/user';
import { AuthService } from 'src/app/shared/services/auth/auth.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {
  name!: string ;
  isLoggedIn!: boolean;
  userDetails!: Observable< IUser | null>;

  constructor(
    private _authService: AuthService,
    private _router: Router
  ) {}

  ngOnInit(): void {
    this.userDetails = this._authService.userDetails$;
  }
}
