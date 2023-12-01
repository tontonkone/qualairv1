// register-form.component.ts
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SignupRequestPayload } from 'src/app/shared/models/auth/singnup-request.payload.ts';
import { FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent implements OnInit {
  @Input() isError!: string;
  @Output() signupEvent = new EventEmitter<SignupRequestPayload>();


  signupRequestPayload: SignupRequestPayload = {
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  };
  signupForm!: FormGroup;

  ngOnInit() {
    this.signupForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
    });
  }

  signup() {

    this.signupRequestPayload.email = this.signupForm.get('email')?.value;
    this.signupRequestPayload.firstName = this.signupForm.get('firstName')?.value;
    this.signupRequestPayload.lastName = this.signupForm.get('lastName')?.value;
    this.signupRequestPayload.password = this.signupForm.get('password')?.value;

    this.signupEvent.emit(this.signupRequestPayload);
  }
}
