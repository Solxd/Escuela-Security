import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private url = 'http://localhost:8081/api/auth';
  constructor(private http: HttpClient) {}
  login(username: string, password: string): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.url}/login`, { username, password })
      .pipe(tap(r => sessionStorage.setItem('token', r.token)));
  }
  getToken(): string | null { return sessionStorage.getItem('token'); }
  estaLogueado(): boolean {
    const t = this.getToken();
    return !!t && t !== 'undefined' && t !== 'null';
  }
  logout(): void { sessionStorage.removeItem('token'); }
}