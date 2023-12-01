import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { IUser } from 'src/app/shared/models/userModel/user';
import { AuthService } from 'src/app/shared/services/auth/auth.service';

@Component({
  selector: 'app-user-center',
  templateUrl: './user-center.component.html',
  styleUrls: ['./user-center.component.scss']
})
export class UserCenterComponent implements OnInit {
  userDetails!: Observable<IUser | null>;

  constructor(private _authService: AuthService,
    private _router: Router) {

  }
  ngOnInit(): void {
    this.userDetails = this._authService.userDetails$;
  }

  logout() {
    this._authService.logout();
    this._router.navigate(['']);
  }

}
