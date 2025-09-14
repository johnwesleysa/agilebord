import { Routes } from '@angular/router';
import { Login } from './auth/login/login'
import { Register } from './auth/register/register'
import { MainLayout } from './layouts/main-layout/main-layout'
import { Dashboard } from './pages/dashboard/dashboard'

export const routes: Routes = [
  // Rotas de autenticação (não usam o layout principal)
  { path: 'login', component: Login },
  { path: 'register', component: Register },

  {
    path: '',
    component: MainLayout,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: Dashboard }


    ]
  },

  { path: '**', redirectTo: '' }
];
