import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import{ environment }from '../../environments/enviromentc';

export interface Categoria {
  id?: number;
  nombre: string;
  descripcion: string;
  fechaCreacion: string;
}

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private apiUrl = `${environment.apiBaseUrl}/categories`; // URL backend

  constructor(private http: HttpClient) {}

  getAll(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.apiUrl);
  }

  getById(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.apiUrl}/${id}`);
  }

  create(data: Omit<Categoria, 'id' | 'fechaCreacion'>): Observable<Categoria> {
    return this.http.post<Categoria>(this.apiUrl, data);
  }

  update(id: number, data: Partial<Categoria>): Observable<Categoria> {
    return this.http.put<Categoria>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
