import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task, TaskUpdateDTO } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private sprintsApiUrl = '/api/sprints';
  private tasksApiUrl = '/api/tasks';

  constructor(private http: HttpClient) { }

  createTask(sprintId: number, taskData: any): Observable<Task> {
    return this.http.post<Task>(`/api/sprints/${sprintId}/tasks`, taskData);
  }

  // Busca todas as tarefas de uma sprint
  getTasksForSprint(sprintId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.sprintsApiUrl}/${sprintId}/tasks`);
  }

  // Atualiza uma tarefa (usado para o drag-and-drop)
  updateTask(taskId: number, taskData: TaskUpdateDTO): Observable<Task> {
    return this.http.put<Task>(`${this.tasksApiUrl}/${taskId}`, taskData);
  }
}
