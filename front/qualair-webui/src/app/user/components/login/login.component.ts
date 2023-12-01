import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequestPayload } from 'src/app/shared/models/auth/login-request.payload';
import { LoginResponse } from 'src/app/shared/models/auth/login-response.payload';
import { IUser } from 'src/app/shared/models/userModel/user';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { ToastService } from 'src/app/shared/services/toast.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isError!: boolean;
  userDetails: LoginResponse | IUser| null = null;

  constructor(
    private _authService: AuthService,
    private router: Router,
    ) {
  }

  ngOnInit(): void {
    this._authService.userDetails$.subscribe((userDetailsReceved)=>{
      this.userDetails = userDetailsReceved;
    })
    
  }

  login(loginRequestPayload: LoginRequestPayload) {
    this._authService.login(loginRequestPayload).subscribe({
      next: (data) => {
        this.isError = false;
        this.router.navigateByUrl('');
      },
      error: (err) => {
        this.isError = true;
      },
    });
  }
}
