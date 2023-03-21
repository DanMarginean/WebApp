import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {ToastMessegesService} from "../toast-messeges/toast-messeges.service";

@Injectable()
export class RegisterInterceptor implements HttpInterceptor {

  constructor(private popup:ToastMessegesService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError((err) => {
      if (err instanceof HttpErrorResponse){
        if(err.status === 500){
          this.popup.showToast(err.message,"error");
        }
      }
      return throwError(err);
    }));
  }
}
