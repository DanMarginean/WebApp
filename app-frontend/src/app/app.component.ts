import {Component, OnInit} from '@angular/core';
import {AuthService} from "./authentication/auth.service";
import {Router} from "@angular/router";
import {ItemCardService} from "./itemcard/item-card.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app-frontend';
  isHover = false;

  constructor(private auth: AuthService,
              private route: Router,
              private itemCardService: ItemCardService) {


  }

  ngOnInit() {
    if (this.auth.isTokenExpired()) {
      this.logOut()
    }
    this.itemCardService.getAllItemCards();
  }

  logOut() {
    this.auth.logOut();
  }

  logIn() {
    this.route.navigateByUrl("/login");
  }

  isLoggedIn(): Boolean {
    return this.auth.isLoggedIn();

  }

  isLoggedOut(): Boolean {
    return this.auth.isLoggedOut();
  }
}
