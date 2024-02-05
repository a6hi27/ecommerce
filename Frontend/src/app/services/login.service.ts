import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

export interface LoginData {
  email: string
  password: string
}

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  constructor(private httpclient: HttpClient) {

  }
  loginUser(loginForm: LoginData): Observable<any> {
    return this.httpclient.get(`http://localhost:8080/users/${loginForm.email}`).pipe(
      tap({
        next: response => console.log('Response:', response),
        error: error => console.log('Error', error)
      })
    )
  }
}

