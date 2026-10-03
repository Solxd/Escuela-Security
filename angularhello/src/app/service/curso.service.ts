import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Curso { id?: number; ciclo_lectivo: number; division: string; grado: string; turno: string; cupo_maximo: number; }

@Injectable({ providedIn: 'root' })
export class CursoService {
  private apiUrl = 'http://localhost:8081/cursos';
  constructor(private http: HttpClient) {}
  listarCursos(): Observable<Curso[]> { return this.http.get<Curso[]>(this.apiUrl); }
  agregarCurso(curso: Curso): Observable<Curso> { return this.http.post<Curso>(this.apiUrl, curso); }
  obtenerPorId(id: number): Observable<Curso> { return this.http.get<Curso>(`${this.apiUrl}/${id}`); }
}