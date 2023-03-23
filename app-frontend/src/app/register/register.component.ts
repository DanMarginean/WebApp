import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {RegisterService} from "./register.service";
import {Router} from "@angular/router";
import {ToastMessegesService} from "../toast-messeges/toast-messeges.service";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),//validator sa verifice direct din campuri daca parola ii egala cu parola c
    // confirmPassword: new FormControl('')
    password: new FormControl(''),
  });
  constructor(private registerService:RegisterService,
              private router:Router,
              private popup:ToastMessegesService) { }

  ngOnInit(): void {

  }

  register(){
    this.registerService.register(this.registerForm.value.firstname,this.registerForm.value.lastname,this.registerForm.value.email,this.registerForm.value.password)
      .subscribe(userData=>{
        catchError((err)=>{
          if (err instanceof HttpErrorResponse){
            if(err.status === 500){
              this.popup.showToast("email existent","error");
            }
          }
          return throwError(err);
        })
        console.log(userData);
      this.router.navigateByUrl('/home');
      this.popup.showToast('Cont creat.Confirmati email',"info")});
  }
}
