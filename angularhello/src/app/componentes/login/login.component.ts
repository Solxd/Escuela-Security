import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  mensajeError = '';
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({ username: ['', Validators.required], password: ['', Validators.required] });
  }
  iniciarSesion(): void {
    if (this.loginForm.invalid) { this.loginForm.markAllAsTouched(); return; }
    this.mensajeError = '';
    const { username, password } = this.loginForm.value;
    this.authService.login(username, password).subscribe({
      next: () => this.router.navigate(['/alumnos']),
      error: () => this.mensajeError = 'Usuario o contraseña incorrectos'
    });
  }
}