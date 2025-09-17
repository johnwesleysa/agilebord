import { Component } from '@angular/core';
import { ProjectStatusCard } from '../../components/project-status-card/project-status-card';


@Component({
  selector: 'app-projects',
  imports: [ProjectStatusCard],
  templateUrl: './projects.html',
  styleUrl: './projects.scss'
})
export class Projects {

}
