import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DragDropModule, CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { TaskCard } from '../../components/task-card/task-card';
import { TaskForm } from '../../components/task-form/task-form';
import { TaskService } from '../../services/task';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [CommonModule, DragDropModule, TaskCard, TaskForm],
  templateUrl: './tasks.html',
  styleUrl: './tasks.scss'
})
export class Tasks implements OnInit {
  // Arrays para cada coluna do Kanban
  todo: Task[] = [];
  inProgress: Task[] = [];
  done: Task[] = [];

  isModalOpen = false;
  currentSprintId = 1;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getTasksForSprint(this.currentSprintId).subscribe(tasks => {
      // Compara com as strings exatas que a API envia
      this.todo = tasks.filter(t => t.status === 'A_FAZER');
      this.inProgress = tasks.filter(t => t.status === 'EM_PROGRESSO');
      this.done = tasks.filter(t => t.status === 'CONCLUIDO');
    });
  }

  drop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      const task = event.previousContainer.data[event.previousIndex];
      const newContainerId = event.container.id;
      let newStatus: string; // <-- O tipo agora é string

      if (newContainerId === 'inProgressList') {
        newStatus = 'EM_PROGRESSO';
      } else if (newContainerId === 'doneList') {
        newStatus = 'CONCLUIDO';
      } else {
        newStatus = 'A_FAZER';
      }

      this.taskService.updateTask(task.id, { ...task, status: newStatus }).subscribe({
        next: (updatedTask) => {
          task.status = updatedTask.status;
          transferArrayItem(
            event.previousContainer.data,
            event.container.data,
            event.previousIndex,
            event.currentIndex
          );
        },
        error: (err) => {
          console.error("Falha ao atualizar a tarefa", err);
          // TODO: Reverter a mudança visual se a API falhar
        }
      });
    }
  }

  openCreateTaskModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
  }

  handleTaskCreated(): void {
    this.loadTasks();
    this.closeModal();
  }
}
