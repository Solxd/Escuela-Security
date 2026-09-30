import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

interface LoginRequest {
  username: string;
  password: string;
}

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'http://localhost:8081/api/auth';

  constructor(private http: HttpClient) {}

  login(
    username: string,
    password: string
  ): Observable<AuthResponse> {

    const datos: LoginRequest = {
      username: username,
      password: password
    };

    return this.http
      .post<AuthResponse>(
        `${this.url}/login`,
        datos
      )
      .pipe(
        tap((respuesta) => {
          localStorage.setItem('token', respuesta.token);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  estaLogueado(): boolean {
    return this.getToken() !== null;
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}