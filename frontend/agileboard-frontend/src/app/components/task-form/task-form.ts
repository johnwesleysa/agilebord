import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../../services/task';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './task-form.html',
  styleUrls: ['./task-form.scss']
})
export class TaskForm {
  @Input() sprintId!: number; // Recebe o ID da sprint onde a tarefa será criada
  @Output() taskCreated = new EventEmitter<void>();
  @Output() closeModal = new EventEmitter<void>();

  taskData = {
    title: '',
    description: '',
    priorityLevel: 'LOW'
  };

  constructor(private taskService: TaskService) {}

  onSubmit(): void {
    if (!this.sprintId) {
      console.error("Sprint ID is required to create a task.");
      return;
    }
    this.taskService.createTask(this.sprintId, this.taskData).subscribe(() => {
      this.taskCreated.emit();
      this.close();
    });
  }

  close(): void {
    this.closeModal.emit();
  }
}
