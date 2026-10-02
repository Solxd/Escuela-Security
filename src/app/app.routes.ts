import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'alumnos', canActivate: [authGuard], loadComponent: () => import('./componentes/alumnos/alumnos.component').then(m => m.AlumnosComponent) },
  { path: 'cursos', canActivate: [authGuard], loadComponent: () => import('./componentes/cursos/cursos.component').then(m => m.CursosComponent) },
  { path: 'administracion', canActivate: [authGuard], loadComponent: () => import('./componentes/administracion/administracion.component').then(m => m.AdministracionComponent) },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];