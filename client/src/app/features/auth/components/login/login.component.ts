import {Component, OnInit} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import {AuthService} from "../../services/auth.service";
import {SessionInformation} from "../../interfaces/sessionInformation.interface";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public onError = false;
  public errorMessage = "";

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: AuthService) {
  }

  ngOnInit(): void {

  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (sessionInformation: SessionInformation) => {
        this.sessionService.save(sessionInformation);
        this.router.navigate(['/chat']);
      },
      error: (error) => {
        this.onError = true;
        this.errorMessage = error.error.message;
        console.log(error);
      }
    });
  }
  public goHome(): void {
    this.router.navigate(['']);
  }
}
