import { Injectable } from '@angular/core';
import {JwtConfig, JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(public jwtHelper: JwtHelperService,
              private router:Router) { }

  public isLoggedIn(): boolean {
    const token = localStorage.getItem('token')
    return !this.jwtHelper.isTokenExpired(token);
  }
  isTokenExpired():Boolean{
    const token = localStorage.getItem('token')

    return this.jwtHelper.isTokenExpired(token)
  }
  isLoggedOut():boolean{
    return !this.isLoggedIn();
  }
  getRole(){
    return localStorage.getItem('role');
  }

  getToken() {
    return localStorage.getItem('token');
  }
  logOut(){
    localStorage.clear();
    this.router.navigateByUrl('/login')
  }
}
