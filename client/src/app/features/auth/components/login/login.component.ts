import {Component} from '@angular/core';
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
export class LoginComponent {
  public onError = false;
  public errorMessage = "";

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$')
      ]
    ],
  });
  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: AuthService) {
  }
  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (sessionInformation: SessionInformation) => {
        this.sessionService.save(sessionInformation);
        this.router.navigate(['/']);
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
