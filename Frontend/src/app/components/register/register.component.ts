import { Component, OnInit } from '@angular/core';
import { RegisterService } from './register.service';
import { SnackbarService } from './snackbar.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';



@Component({
  selector: 'eca-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private registerService: RegisterService, private snackbarService: SnackbarService, private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?":{}|<>]).*$/)]],
      confirmPassword: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    // Subscribe to password changes for dynamic matching validation
    this.registerForm.get('password')!.valueChanges.subscribe(() => {
      this.registerForm.get('confirmPassword')!.updateValueAndValidity();
    });
    this.registerForm.get('confirmPassword')!.valueChanges.subscribe(() => {
      this.registerForm.get('confirmPassword')!.updateValueAndValidity();
    });
  }

  get emailInvalid(): boolean {
    const emailControl = this.registerForm.get('email');
    return emailControl?.invalid && (emailControl?.dirty || emailControl?.touched) || false;
  }

  get passwordInvalid(): boolean {
    const passwordControl = this.registerForm.get('password');
    return passwordControl?.invalid && (passwordControl?.dirty || passwordControl?.touched) || false;
  }

  get confirmPasswordInvalid(): boolean {
    const passwordControl = this.registerForm.get('password');
    const confirmPasswordControl = this.registerForm.get('confirmPassword');
    const passwordsMatch =
      passwordControl?.value === confirmPasswordControl?.value || confirmPasswordControl?.pristine;

    // Update the validity of confirmPassword dynamically based on password match
    confirmPasswordControl?.setErrors(passwordsMatch ? null : { mismatch: true });

    return (
      confirmPasswordControl?.invalid &&
      (confirmPasswordControl?.dirty || confirmPasswordControl?.touched) || false
    );
  }


  onSubmit(): void {

    const email = this.registerForm.get('email')?.value;
    const name = this.registerForm.get('name')?.value;
    const password = this.registerForm.get('password')?.value;

    const formData = {
      email: email,
      name: name,
      password: password
    };

    this.registerService.registerUser(formData).subscribe({
      next: response => {
        // Handle the response
        this.snackbarService.openSnackBar(response);
        this.router.navigate(['/home']);
      },
      error: error => {
        // Handle the error
        console.log(error)
        this.snackbarService.openSnackBar('User not created', 5000);
      }
    }
    );
  }
}
