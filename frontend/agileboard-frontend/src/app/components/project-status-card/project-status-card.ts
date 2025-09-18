import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Project } from '../../models/project.model';
import { ProjectService } from '../../services/project';

@Component({
  selector: 'app-project-status-card',
  imports: [],
  templateUrl: './project-status-card.html',
  styleUrl: './project-status-card.scss'
})
export class ProjectStatusCard {
  @Input() project: Project | null = null;
  @Output() projectDeleted = new EventEmitter<number>(); 

  constructor(private projectService: ProjectService) {}

  // ... getStatusClass ...

  onDelete(event: MouseEvent): void {
    event.stopPropagation(); // Impede que o clique propague para outros elementos
    if (!this.project) return;

    // Ação de confirmação
    const confirmation = confirm(`Tem certeza que deseja deletar o projeto "${this.project.title}"?`);
    if (confirmation) {
      this.projectService.deleteProject(this.project.id).subscribe(() => {
        // Notifica o componente pai que este projeto foi deletado
        this.projectDeleted.emit(this.project!.id);
      });
    }
  }
}

