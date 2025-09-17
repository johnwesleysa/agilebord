import { Routes } from '@angular/router';
import { Login } from './auth/login/login'
import { Register } from './auth/register/register'
import { MainLayout } from './layouts/main-layout/main-layout'
import { Dashboard } from './pages/dashboard/dashboard'
import { Projects } from './pages/projects/projects';
import { Sprints } from './pages/sprints/sprints';
import { Tasks } from './pages/tasks/tasks';

export const routes: Routes = [
  // Rotas de autenticação (não usam o layout principal)
  { path: 'login', component: Login },
  { path: 'register', component: Register },

  {
    path: '',
    component: MainLayout,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: Dashboard },
      { path: 'projects', component: Projects},
      { path: 'sprints', component: Sprints},
      { path: 'tasks', component: Tasks}


    ]
  },

  { path: '**', redirectTo: '' }
];
