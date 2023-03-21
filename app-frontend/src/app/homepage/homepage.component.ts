import {Component, Input, OnInit} from '@angular/core';
import {AddDialogComponent} from "../add-dialog/add-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {NgbCarousel} from "@ng-bootstrap/ng-bootstrap";
import {ItemCardService} from "../itemcard/item-card.service";
import {AuthService} from "../authentication/auth.service";
import {ItemAdd} from "../models/itemAdd";
import {ItemCard} from "../models/itemCard";
import {Router} from "@angular/router";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  isExpanded:boolean=true;
  showNavigationArrows = false;
  showNavigationIndicators = false;
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
  // itemCards:ItemCard[]=[];
  isPressed:boolean=false;
  // itemcard:ItemCard={
  //   name:"name",
  //   price:"price",
  //   descriere:"desc"
  // }

  constructor(private dialog:MatDialog,
              private carousel:NgbCarousel,
              private auth:AuthService,
              private router:Router,
              private itemcardService:ItemCardService) {
    carousel.showNavigationArrows = true;
    carousel.showNavigationIndicators = true;
  }

  ngOnInit(): void {
    // this.itemCardService.getAllItemCards();
  }
extendState():void{
    this.isExpanded=!this.isExpanded;

}
isAdmin():Boolean{
   if(this.auth.getRole()==="ADMIN") return true;
   else return false
}
openAddDialog(){
    this.dialog.open(AddDialogComponent);
}
showItems(){
    this.router.navigateByUrl("home");
}
  // getAllItems():void{
  //   this.itemcardService.getAllItemCards().subscribe(itemCards =>{
  //     console.log(itemCards)
  //     // this.itemCards=itemCards
  //   });
  //     this.isPressed=!this.isPressed;
  // }
}
