import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})

export class Login {
  // Objeto para armazenar os dados do formulário de login
  credentials = {
    username: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    const dataForApi = {
      username: this.credentials.username,
      password: this.credentials.password
    };

    this.authService.login(dataForApi).subscribe({
      next: (response) => {
        console.log('Login bem-sucedido!', response);

        // 5. Salva o token no localStorage
        localStorage.setItem('authToken', response.token);

        // 6. Redireciona para o dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Erro no login!', error);
        alert('Usuário ou senha inválidos.');
      }
    });
  }
}
