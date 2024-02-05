import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationData } from './register.model';
import { tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private apiUrl = "http://localhost:8080";
  constructor(private http: HttpClient) { }

  registerUser(userData: RegistrationData): Observable<any> {
    return this.http.post(`${this.apiUrl}/users`, userData, { responseType: 'text' }).pipe(
      tap({
        next: response => console.log('Response:', response),
        error: error => console.error('Error:', error)
      })
    );
  }
}
