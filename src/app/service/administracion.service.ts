import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Personal { id?: number; nombre: string; apellido: string; email: string; cargo: string; }

@Injectable({ providedIn: 'root' })
export class AdministracionService {
  private apiUrl = 'http://localhost:8081/personal';
  constructor(private http: HttpClient) {}
  listarPersonal(): Observable<Personal[]> { return this.http.get<Personal[]>(this.apiUrl); }
  agregarPersonal(p: Personal): Observable<Personal> { return this.http.post<Personal>(this.apiUrl, p); }
  obtenerPorId(id: number): Observable<Personal> { return this.http.get<Personal>(`${this.apiUrl}/${id}`); }
}