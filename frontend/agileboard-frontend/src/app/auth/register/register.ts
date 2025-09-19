import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})

export class Register {
  formData = {
    fullName: '',
    username: '', // Enviando o email como username
    password: '',
    confirmPassword: ''
  };

  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    this.errorMessage = null;
    this.successMessage = null;

    // Validando confirmação de senha
    if (this.formData.password !== this.formData.confirmPassword){
      this.errorMessage = "As senhas não coincidem.";
      return;
    }

    // Validar força da senha
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordRegex.test(this.formData.password)) {
      this.errorMessage = "A senha deve ter no mínimo 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.";
      return;
    }

    const dataForApi = {
      username: this.formData.username,
      fullName: this.formData.fullName,
      password: this.formData.password
    }

    this.authService.register(dataForApi).subscribe({
      next: (response) => {
        this.successMessage = "Registro bem-sucedido! Redirecionando para login...";
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        this.errorMessage = error.error.message || "Falha no registro. Tente novamente.";
      }
    });
  }
}
