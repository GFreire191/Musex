// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private UsersUrl = 'http://localhost:8080/api/users';
  private loggedIn = new BehaviorSubject<boolean>(this.hasValidToken());

  constructor(private http: HttpClient) {}

  private hasValidToken(): boolean {
    const token = localStorage.getItem('authToken');
    const expiration = localStorage.getItem('authTokenExpiration');
    if (!token || !expiration) {
      return false;
    }
    const now = new Date().getTime();
    return now < parseInt(expiration, 10);
  }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user).pipe(
      tap((response: any) => {
        this.storeToken(response.token);
      })
    );
  }

  logIn(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, user).pipe(
      tap((response: any) => {
        this.storeToken(response.token);
      })
    );
  }

  private storeToken(token: string) {
    const expiresIn = 7200;
    const expirationTime = new Date().getTime() + expiresIn * 1000;
    localStorage.setItem('authToken', token);
    localStorage.setItem('authTokenExpiration', expirationTime.toString());
    this.loggedIn.next(true);
  }

  getUserId(): string | null {
    const token = localStorage.getItem('authToken');
    if (!token) {
      return null;
    }
    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    const user = JSON.parse(decodedPayload);
    return user.userId;
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('authTokenExpiration');
    this.loggedIn.next(false);

    
  }

  getUserInfo(id: string): Observable<any> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(`${this.UsersUrl}/${id}`, { headers });
  }
}
