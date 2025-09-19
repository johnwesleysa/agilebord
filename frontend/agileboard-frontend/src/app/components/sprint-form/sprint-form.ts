import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SprintService, SprintCreateDTO } from '../../services/sprint';

@Component({
  selector: 'app-sprint-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sprint-form.html',
  styleUrls: ['./sprint-form.scss']
})
export class SprintForm implements OnInit {
  // Recebe o ID do projeto do componente pai
  @Input() projectId!: number;

  // Avisa o componente pai quando o formulário for enviado ou fechado
  @Output() formSubmitted = new EventEmitter<void>();
  @Output() closeModal = new EventEmitter<void>();

  sprint: SprintCreateDTO = { title: '', description: '' };
  // TODO: Adicionar lógica para modo de edição no futuro

  constructor(private sprintService: SprintService) {}

  ngOnInit(): void {
    // Lógica de edição pode ser adicionada aqui no futuro
  }

  onSubmit(): void {
    if (!this.projectId) {
      console.error("ID do projeto é necessário para criar uma sprint.");
      return;
    }

    this.sprintService.createSprint(this.projectId, this.sprint).subscribe(() => {
      this.formSubmitted.emit();
      this.close();
    });
  }

  close(): void {
    this.closeModal.emit();
  }
}
