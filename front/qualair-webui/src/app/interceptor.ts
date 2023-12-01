    import { Injectable } from '@angular/core';
    import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
    import { Observable, BehaviorSubject, throwError } from 'rxjs';
    import { catchError, switchMap, take, filter } from 'rxjs/operators';
    import { AuthService } from './shared/services/auth/auth.service';
    import { LoginResponse } from './shared/models/auth/login-response.payload';

    @Injectable()

    export class Interceptor implements HttpInterceptor {

        isTokenRefreshing = false; // Un indicateur pour vérifier si le rafraîchissement du token est en cours.
        refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null); // Un sujet BehaviorSubject pour gérer le rafraîchissement du token.

        constructor(public authService: AuthService) { }

        // Méthode intercept pour gérer les requêtes HTTP.
        intercept(req: HttpRequest<any>, next: HttpHandler):
            Observable<HttpEvent<any>> {

            // Si la requête est une demande de rafraîchissement de token ou de connexion, laisser passer sans modification.
            if (req.url.indexOf('refresh') !== -1 || req.url.indexOf('login') !== -1) {
                console.log('Refreshing token', req.url);
                return next.handle(req);
            }

            // Récupérer le token JWT de l'AuthService.
            const jwtToken = this.authService.getJwtToken();

            // Si un token JWT est disponible, ajouter le token JWT aux en-têtes de la requête.
            if (jwtToken) {
                if (req.url.includes("https://france-geojson.gregoiredavid.fr") || req.url.includes("https://api.waqi.info/") ) {
                    return next.handle(req);
                }
                return next.handle(this.addToken(req, jwtToken)).pipe(catchError(error => {
                    // Gérer les erreurs HTTP.
                    if (error instanceof HttpErrorResponse
                        && error.status === 403) {
                        // Si l'erreur est une erreur 403 (forbidden), gérer les erreurs d'authentification.
                        return this.handleAuthErrors(req, next);
                    } else {
                        return throwError(error);
                    }
                }));
            }

       
            // Si aucun token JWT n'est disponible, laisser passer la requête sans modification.
            return next.handle(req);

        }

        // Méthode pour gérer les erreurs d'authentification (par exemple, rafraîchir le token).
        private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler)
            : Observable<HttpEvent<any>> {

            if (!this.isTokenRefreshing) {
                this.isTokenRefreshing = true;
                this.refreshTokenSubject.next(null);

                // Appeler la méthode refreshToken de l'AuthService pour obtenir un nouveau token.
                return this.authService.refreshToken().pipe(
                    switchMap((refreshTokenResponse: LoginResponse) => {
                        this.isTokenRefreshing = false;
                        console.log(refreshTokenResponse.authenticationToken)
                        this.refreshTokenSubject
                            .next(refreshTokenResponse.authenticationToken);

                        // Ajouter le nouveau token JWT aux en-têtes de la requête et poursuivre la requête
                        return next.handle(this.addToken(req,
                            refreshTokenResponse.authenticationToken));
                    })
                )
            } else {
                return this.refreshTokenSubject.pipe(
                    filter(result => result !== null),
                    take(1),
                    switchMap((res) => {
                        // Ajouter le token JWT rafraîchi aux en-têtes de la requête et poursuivre la requête
                        return next.handle(this.addToken(req,
                            this.authService.getJwtToken()))
                    })
                );
            }
        }

        // Méthode pour ajouter le token JWT aux en-têtes de la requête
        addToken(req: HttpRequest<any>, jwtToken: any) {
            return req.clone({
                headers: req.headers.set('Authorization',
                    'Bearer ' + jwtToken)
            });
        }

    }
