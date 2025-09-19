import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProjectService, ProjectCreateDTO } from '../../services/project';
import { Project } from '../../models/project.model';

@Component({
  selector: 'app-project-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './project-form.html',
  styleUrls: ['./project-form.scss']
})
export class ProjectForm implements OnInit {
  @Input() projectId: number | null = null; // Recebe o ID para edição
  @Output() formSubmitted = new EventEmitter<void>();
  @Output() closeModal = new EventEmitter<void>();

  project: ProjectCreateDTO = { title: '', description: '' };
  isEditMode = false;

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {
    if (this.projectId) {
      this.isEditMode = true;
      this.projectService.getProjectById(this.projectId).subscribe(data => {
        this.project = data;
      });
    }
  }

  onSubmit(): void {
    const operation = this.isEditMode && this.projectId
      ? this.projectService.updateProject(this.projectId, this.project)
      : this.projectService.createProject(this.project);

    operation.subscribe(() => {
      this.formSubmitted.emit();
      this.close();
    });
  }

  close(): void {
    this.closeModal.emit();
  }
}
