import {Component, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {AddDialogComponent} from "../add-dialog/add-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {NgbCarousel} from "@ng-bootstrap/ng-bootstrap";
import {AuthService} from "../authentication/auth.service";
import {Router} from "@angular/router";
import {ItemCardService} from "../itemcard/item-card.service";
import {ItemCard} from "../models/itemCard";
import {ItemcardComponent} from "../itemcard/itemcard.component";
import {CommunicationService} from "../communication.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  isExpanded: boolean = true;
  showNavigationArrows = false;
  showNavigationIndicators = false;
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
  // itemCards:ItemCard[]=[];
  isPressed: boolean = false;
  itemCards: ItemCard[] = [];


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
              private auth: AuthService,
              private route: Router,
              private itemCardService: ItemCardService,
              private dialog: MatDialog,
              private refresh: CommunicationService,
              private items: ItemcardComponent,
              private carousel: NgbCarousel,) {
  }

  ngOnInit() {
    if (this.auth.isTokenExpired()) {
      this.logOut()
    }
    // this.itemCardService.getAllItemCards();
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

  extendState(): void {
    this.isExpanded = !this.isExpanded;

  }

  isAdmin(): Boolean {
    if (this.auth.getRole() === "ADMIN") return true;
    else return false
  }

  openAddDialog() {
    this.dialog.open(AddDialogComponent);
    this.dialog.afterAllClosed.subscribe((next) => this.items.getAllItems())
  }

  showItems() {
    this.route.navigateByUrl("home");
  }

}
