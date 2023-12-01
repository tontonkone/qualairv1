import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LoginRequestPayload } from 'src/app/shared/models/auth/login-request.payload';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  @Input() isError!: boolean;
  @Output() loginSubmit: EventEmitter<LoginRequestPayload> = new EventEmitter<LoginRequestPayload>();

  loginForm!: FormGroup;
  loginRequestPayload: LoginRequestPayload;

  constructor() {
    this.loginRequestPayload = {
      email: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  login() {

    this.loginRequestPayload.email = this.loginForm.get('username')?.value;
    this.loginRequestPayload.password = this.loginForm.get('password')?.value;

    this.loginSubmit.emit(this.loginRequestPayload);
  }
}
