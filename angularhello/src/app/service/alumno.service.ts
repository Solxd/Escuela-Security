import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Alumno { id?: number; nombre: string; apellido: string; dni: string; email: string; }

@Injectable({ providedIn: 'root' })
export class AlumnoService {
  private apiUrl = 'http://localhost:8081/alumnos';
  constructor(private http: HttpClient) {}
  listarAlumnos(): Observable<Alumno[]> { return this.http.get<Alumno[]>(this.apiUrl); }
  agregarAlumno(alumno: Alumno): Observable<Alumno> { return this.http.post<Alumno>(this.apiUrl, alumno); }
  obtenerConCurso(id: number): Observable<any> { return this.http.get<any>(`${this.apiUrl}/${id}/con-curso`); }
}