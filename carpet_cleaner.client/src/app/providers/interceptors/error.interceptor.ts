import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AuthorizationService} from "@services/authorization.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authorizationService: AuthorizationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 401) {
        // auto logout if 401 response returned from api
        this.authorizationService.logout();
      }

      let error = err.error ? err.error.error : undefined;
      error = error || err.message || err.statusText;

      return throwError(error);
    }))
  }

}
