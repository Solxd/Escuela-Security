import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Curso {
  id?: number;
  ciclo_lectivo: string;
  division: string;
  grado: string;
  turno: string;
  cupo_maximo: string;
}

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  private url = 'http://localhost:8081/cursos';

  constructor(private http: HttpClient) {}

  private obtenerHeaders(): HttpHeaders {

    const token = localStorage.getItem('token');

    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

  }

  obtenerCursos(): Observable<Curso[]> {

    return this.http.get<Curso[]>(
      this.url,
      {
        headers: this.obtenerHeaders()
      }
    );

  }

  crearCurso(curso: Curso): Observable<Curso> {

    return this.http.post<Curso>(
      this.url,
      curso,
      {
        headers: this.obtenerHeaders()
      }
    );

  }

}