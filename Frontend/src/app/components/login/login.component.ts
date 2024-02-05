import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { UserService } from '../../services/user.service';
import { SnackbarService } from '../register/snackbar.service';

@Component({
  selector: 'eca-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {

  loginForm: FormGroup

  constructor(private router: Router, private fb: FormBuilder, private loginService: LoginService, private userService: UserService, private snackBar: SnackbarService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    })
  }

  public redirectToHome() {
    const email = this.loginForm.get("email")?.value
    const password = this.loginForm.get("password")?.value
    const loginformData = { email: email, password: password }
    this.loginService.loginUser(loginformData).subscribe({
      next: response => {
        //Set the authenticated user globally
        this.userService.setUser(response)
        this.router.navigate(['/home'])
      },
      error: error => {
        this.snackBar.openSnackBar(error)
      }
    })
  }
}
