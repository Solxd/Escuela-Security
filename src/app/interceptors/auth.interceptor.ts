import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AuthService } from '../service/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(AuthService);
  const router = inject(Router);
  const token = auth.getToken();
  const peticion = token ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }) : req;
  return next(peticion).pipe(catchError(err => {
    if (err.status === 401 && !req.url.includes('/auth/')) {
      auth.logout();
      router.navigate(['/login']);
    }
    return throwError(() => err);
  }));
};