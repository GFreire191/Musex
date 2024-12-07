// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  registerAdmin(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register-admin`, user);
  }

  logIn(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, user);
  }
  
}