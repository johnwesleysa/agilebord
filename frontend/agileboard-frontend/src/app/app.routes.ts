import { Routes } from '@angular/router';
import { Login } from './auth/login/login'
import { Register } from './auth/register/register'

export const routes: Routes = [
  //rota padrão caso a url esteja vazia
  {path: '', redirectTo: '/login', pathMatch: 'full'},

  //rota para tela de login
  {path:'login', component: Login},

  //rota para tela de registro
  {path:'register', component: Register}

];
