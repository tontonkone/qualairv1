import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SignupRequestPayload } from 'src/app/shared/models/auth/singnup-request.payload.ts';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { ToastrService } from 'ngx-toastr';
import { ToastService } from 'src/app/shared/services/toast.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  isError!: string;

  constructor(private _authService: AuthService, 
    private router: Router,
    private toast: ToastService
    ) { }

signup(signupRequestPayload: SignupRequestPayload){
  this._authService.signup(signupRequestPayload)
    .subscribe({
      next: (data) =>{
        this.router.navigateByUrl('/login');
        this.toast.show('success ok ', { classname: 'bg-success text-light', delay: 3000 });
      },
      error: (err) => {
        const objectError = JSON.parse(err.error) 
        this.isError = objectError.error
      }
    });
  }

}
