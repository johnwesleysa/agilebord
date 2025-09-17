import { Component } from '@angular/core';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { TaskCard } from '../../components/task-card/task-card';
import { CommonModule } from '@angular/common';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-tasks',
  imports: [CommonModule, DragDropModule, TaskCard],
  templateUrl: './tasks.html',
  styleUrl: './tasks.scss'
})

export class Tasks {
  todo = ['Implementar autenticação JWT', 'Implementar cache Redis'];
  inProgress = ['Design da tela de login'];
  done = ['Correção de bugs no checkout'];

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }
}
