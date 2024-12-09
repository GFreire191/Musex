import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = "http://localhost:8080/api/users";
  constructor(private  http: HttpClient, private authService: AuthService) { }
  getUsers():Observable<any>{
    return this.http.get(`${this.apiUrl}`);

  }

getUser(id: number):Observable<any>{
    return this.http.get(`${this.apiUrl}/${id}`);
}


updateUser(id: number, user:any):Observable<any>{
    const token = localStorage.getItem('authToken');
    console.log("Token", token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put(`${this.apiUrl}/${id}`, user, { headers });
}


deleteUser(id: number):Observable<any>{
    return this.http.delete(`${this.apiUrl}/${id}`);
}

}
