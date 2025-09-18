import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService, ProjectCreateDTO } from '../../services/project';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-project-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './project-form.html',
  styleUrls: ['./project-form.scss']
})
export class ProjectForm implements OnInit {
  project: ProjectCreateDTO = { title: '', description: '' };
  isEditMode = false;
  projectId: number | null = null;

  constructor(
    private projectService: ProjectService,
    private router: Router,
    private route: ActivatedRoute // Para ler parâmetros da URL
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode = true;
      this.projectId = +idParam; // o '+' converte string para número
      // Se estamos editando, buscamos os dados do projeto
      this.projectService.getProjectById(this.projectId).subscribe(data => {
        this.project = data;
      });
    }
  }

  onSubmit(): void {
    if (this.isEditMode && this.projectId) {
      this.projectService.updateProject(this.projectId, this.project).subscribe(() => {
        this.router.navigate(['/projects']);
      });
    } else {
      this.projectService.createProject(this.project).subscribe(() => {
        this.router.navigate(['/projects']);
      });
    }
  }
}
