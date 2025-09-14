import { Component } from '@angular/core';
import { StatCard } from '../../components/stat-card/stat-card';
import { SprintProgressCard } from '../../components/sprint-progress-card/sprint-progress-card';
import { SquadRankingCard } from '../../components/squad-ranking-card/squad-ranking-card'

@Component({
  selector: 'app-dashboard',
  imports: [StatCard, SprintProgressCard, SquadRankingCard],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard {

}
