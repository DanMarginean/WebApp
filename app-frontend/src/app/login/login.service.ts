import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";
import {AuthUser} from "../models/auth-user";

@Injectable({
  providedIn: 'root'
})


export class LoginService {
  url = environment.apiURLauth;
  login = environment.apiEndpointsauth.auth

  constructor(private http: HttpClient,
              private router: Router) {
  }

  authenticate(email: string, password: string) {
    return this.http.post<AuthUser>(this.url + this.login, {email, password}).pipe(map(userData => {
      localStorage.setItem('token', userData.token);
      localStorage.setItem('role', userData.role)
      console.log(userData);
      this.router.navigateByUrl("/main")
      return userData;
    }));
  }

}
