import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page, Project } from '../models/project.model';

// DTO para criação/atualização
export interface ProjectCreateDTO {
  title: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private apiUrl = '/api/projects';

  constructor(private http: HttpClient) { }

  getProjects(page: number = 0, size: number = 10): Observable<Page<Project>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<Page<Project>>(this.apiUrl, { params });
  }

  getProjectById(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.apiUrl}/${id}`);
  }

  createProject(projectData: ProjectCreateDTO): Observable<Project> {
    return this.http.post<Project>(this.apiUrl, projectData);
  }

  updateProject(id: number, projectData: ProjectCreateDTO): Observable<Project> {
    return this.http.put<Project>(`${this.apiUrl}/${id}`, projectData);
  }

  deleteProject(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
