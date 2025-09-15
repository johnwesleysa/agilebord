import { Component } from '@angular/core';
import { StatCard } from '../../components/stat-card/stat-card';
import { SprintProgressCard } from '../../components/sprint-progress-card/sprint-progress-card';
import { SquadRankingCard } from '../../components/squad-ranking-card/squad-ranking-card'
import { ProjectStatusCard } from '../../components/project-status-card/project-status-card';
import { ActivityFeedCard } from '../../components/activity-feed-card/activity-feed-card';
import { BurndownChartCard } from '../../components/burndown-chart-card/burndown-chart-card';
@Component({
  selector: 'app-dashboard',
  imports: [StatCard, SprintProgressCard, SquadRankingCard, ProjectStatusCard, ActivityFeedCard, BurndownChartCard],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard {

}
