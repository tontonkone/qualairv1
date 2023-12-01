import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { SignupRequestPayload } from '../../models/auth/singnup-request.payload.ts';
import { LoginResponse } from '../../models/auth/login-response.payload.js';
import { LoginRequestPayload } from '../../models/auth/login-request.payload.js';
import { IUser } from '../../models/userModel/user.js';
import { UserService } from '../userService/user.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _baseUrl = environment.apiUrl;

  // Émetteurs d'événements pour communiquer avec d'autres composants
  @Output() isAdmin: EventEmitter<boolean> = new EventEmitter();
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  // Observable de l'utilisateur actuellement connecté
  private userSubject = new BehaviorSubject<IUser | null>(null);

  get userDetails$() {
    return this.userSubject.asObservable();
  }

  // Payload de rafraîchissement pour les tokens JWT
  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  };

  constructor(
    private _userService: UserService,
    private _httpClient: HttpClient
  ) {
    this.onSiteLoading();
  }

  // Méthode pour s'inscrire en tant qu'utilisateur
  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    console.log(`signup ${JSON.stringify(signupRequestPayload)}`);
    return this._httpClient.post(
      this._baseUrl + '/api/auth/signup',
      signupRequestPayload,
      { responseType: 'text' }
    );
  }

  // Méthode pour se connecter en tant qu'utilisateur
  login(loginRequestPayload: LoginRequestPayload): Observable<any> {
    return this._httpClient
      .post<LoginResponse>(this._baseUrl + '/api/auth/login', loginRequestPayload)
      .pipe(
        map(data => {
          localStorage.setItem('authenticationToken', data.authenticationToken);
          localStorage.setItem('refreshToken', data.authenticationToken);
          const payload = this.getTokenPayload(data.authenticationToken);
          this.userSubject.next(this.getUserFromTokenPayload(payload));
          return data;
        })
      );
  }

  // Méthode pour gérer le chargement initial du site
  onSiteLoading() {
    const authenticationToken = localStorage.getItem('authenticationToken');
    if (null == authenticationToken) return;

    const payload = this.getTokenPayload(authenticationToken);

    // Vérifier la condition
    if (payload.exp * 1000 <= Date.now()) {
      // this.refreshToken();
      return;
    }

    this.userSubject.next(this.getUserFromTokenPayload(payload));
  }

  // Méthode pour obtenir le token JWT actuel
  getJwtToken() {
    return localStorage.getItem('authenticationToken');
  }

  // Méthode pour extraire le payload du token JWT
  private getTokenPayload(jwtToken: string) {
    // Divisez le token en trois parties (header, payload, signature)
    const [headerBase64, payloadBase64, signature] = jwtToken.split('.');
    // Décodage de la partie payload (base64)
    return JSON.parse(atob(payloadBase64));
  }

  // Méthode pour extraire l'utilisateur du payload du token
  private getUserFromTokenPayload(payload: any) {
    if (payload.user) {
      return JSON.parse(payload.user);
    } else {
      throw 'no user in payload';
    }
  }

  // Méthode pour rafraîchir le token JWT
  refreshToken() {
    return this._httpClient
      .post<LoginResponse>(this._baseUrl + '/api/auth/refresh/token', this.refreshTokenPayload)
      .pipe(
        tap(response => {
          localStorage.removeItem('authenticationToken');
          localStorage.removeItem('expiresAt');
          localStorage.setItem('authenticationToken', response.authenticationToken);
          localStorage.setItem('expiresAt', response.expiresAt.toString());
        })
      );
  }

  // Méthode pour se déconnecter de l'application
  logout() {
    this._httpClient
      .post(
        this._baseUrl + '/api/auth/logout',
        this.refreshTokenPayload,
        { responseType: 'text' }
      )
      .subscribe(
        data => {
          console.log(data);
        },
        error => {
          throwError(error);
        }
      );
    localStorage.removeItem('authenticationToken');
    localStorage.removeItem('username');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('expiresAt');
    this.userSubject.next(null);
  }

  // Méthode pour obtenir le nom d'utilisateur
  getUserName() {
    return this.userSubject.value?.email;
  }

  // Méthode pour obtenir le token de rafraîchissement
  getRefreshToken() {
    return localStorage.getItem('refreshToken');
  }

  // Méthode pour vérifier si l'utilisateur est connecté
  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
