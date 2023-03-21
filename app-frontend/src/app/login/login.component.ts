import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "./login.service";
import {User} from "../models/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  logInForm = new FormGroup({
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required),
  });
  user : User ;
  constructor(private route:Router,
              private login:LoginService,
               fb: FormBuilder) {

  }

  ngOnInit(): void {
    // this.logInForm = new FormGroup<any>({
    //   email: new FormControl(null),
    //   password: new FormControl(null),
    // })
  }

  logIn(){
    // this.user.email=this.logInForm.get('email');
    // this.user.password=this.logInForm.get('password');
    this.login.authenticate(this.logInForm.value.email,this.logInForm.value.password).subscribe(response=>console.log(response)

    );
console.log(this.user);
    // this.login.authenticate(this.user).subscribe(next=>{console.log(next);
    //   this.route.navigate(['home']);})

  }
}
