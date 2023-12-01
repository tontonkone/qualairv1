import { CanActivateFn, Router } from '@angular/router';
import { inject } from "@angular/core";
import { AuthService } from 'src/app/shared/services/auth/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const isAuthenticated = authService.isLoggedIn();
  if (isAuthenticated) {
    return true
  } else {
    router.navigateByUrl('/login');
  }
  return true;
};