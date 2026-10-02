import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Administracion {
  id?: number;
  nombre: string;
  apellido: string;
  email: string;
  cargo: string;
}

@Injectable({
  providedIn: 'root'
})
export class AdministracionService {

  private url = 'http://localhost:8081/personal';

  constructor(private http: HttpClient) {}

  private obtenerHeaders(): HttpHeaders {

    const token = localStorage.getItem('token');

    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

  }

  obtenerAdministracion(): Observable<Administracion[]> {

    return this.http.get<Administracion[]>(
      this.url,
      {
        headers: this.obtenerHeaders()
      }
    );

  }

  crearAdministracion(
    administracion: Administracion
  ): Observable<Administracion> {

    return this.http.post<Administracion>(
      this.url,
      administracion,
      {
        headers: this.obtenerHeaders()
      }
    );

  }

}