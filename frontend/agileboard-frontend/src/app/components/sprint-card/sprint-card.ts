import { Component, Input } from '@angular/core';
import { Sprint } from '../../models/sprint.model';

@Component({
  selector: 'app-sprint-card',
  imports: [],
  templateUrl: './sprint-card.html',
  styleUrl: './sprint-card.scss'
})
export class SprintCard {
  @Input() sprint: Sprint | null = null;
}
