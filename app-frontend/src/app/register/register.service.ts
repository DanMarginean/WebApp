import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
url=environment.apiURLauth;
endpoint=environment.apiEndpointsauth.register;
  constructor(private http:HttpClient) { }

  register(firstname:string,lastname:string,email:string,password:string){
    return this.http.post(this.url+this.endpoint,{firstname,lastname,email,password})
  }
}
