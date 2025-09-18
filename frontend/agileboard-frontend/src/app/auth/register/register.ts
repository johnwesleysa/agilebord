import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})

export class Register {
  // Objeto para armazenar todos os dados do formulário
  formData = {
    fullName: '',       // TODO: Adicionar este campo no DTO e na entidade do backend
    username: '',       // O campo 'email' do formulário será nosso 'username'
    password: '',
    confirmPassword: '' // TODO: Adicionar lógica de validação para este campo
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    // Preparamos o objeto apenas com os dados que o backend espera
    const dataForApi = {
      username: this.formData.username,
      password: this.formData.password
    };

    console.log('Dados a serem enviados para a API:', dataForApi);

    // A chamada para o serviço continua a mesma
    this.authService.register(dataForApi).subscribe({
      next: (response) => {
        console.log('Usuário registrado com sucesso!', response);
        alert('Usuário registrado com sucesso! Faça o login para continuar.'); // Um alerta para o usuário
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Erro ao registrar usuário!', error);
        // Exemplo de como mostrar o erro para o usuário
        alert(`Erro ao registrar: ${error.error.message || 'Verifique os dados e tente novamente.'}`);
      }
    });
  }
}
