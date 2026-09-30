import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Alumno {
  id?: number;
  nombre: string;
  apellido: string;
  dni: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  private url = 'http://localhost:8081/alumnos';

  constructor(private http: HttpClient) {}

  private obtenerHeaders(): HttpHeaders {

    const token = localStorage.getItem('token');

    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

  }

  obtenerAlumnos(): Observable<Alumno[]> {

    return this.http.get<Alumno[]>(
      this.url,
      {
        headers: this.obtenerHeaders()
      }
    );

  }

  crearAlumno(alumno: Alumno): Observable<Alumno> {

    return this.http.post<Alumno>(
      this.url,
      alumno,
      {
        headers: this.obtenerHeaders()
      }
    );

  }

}