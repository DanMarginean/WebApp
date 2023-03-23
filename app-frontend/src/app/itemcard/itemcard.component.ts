import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ItemCard} from "../models/itemCard";
import {ItemCardService} from "./item-card.service";
import {AddDialogComponent} from "../add-dialog/add-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {UpdateDialogComponent} from "../update-dialog/update-dialog.component";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-itemcard',
  templateUrl: './itemcard.component.html',
  styleUrls: ['./itemcard.component.css']
})
export class ItemcardComponent implements OnInit {
  itemCards: ItemCard[] = [];
  itemId: string;

  itemcard: ItemCard = {
    id: "id",
    name: "name",
    price: "price",
    descriere: "desc",
    filePath: null,
    bytes: null,
    image: null
  }

  constructor(private itemcardService: ItemCardService,
              private route: ActivatedRoute,
              private router: Router,
              private updateDialog: MatDialog,
              private dialog: MatDialog,
              private sanitizer: DomSanitizer
  ) {
  }

  ngOnInit(): void {
    this.getAllItems();

  }

  getAllItems(): void {
    this.itemcardService.getAllItemCards()
      .subscribe(itemCards => {
        console.log(itemCards)
        this.itemCards = itemCards
      });


  }

  getImageUrl(imageBytes: string): SafeUrl {
    const byteCharacters = atob(imageBytes);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], {type: 'image/jpeg'});
    const imageUrl = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }

  deleteItem(id: string) {
    this.itemcardService.deleteItemById(id).subscribe(response => {
      console.log(response);
      this.getAllItems();
    });
    console.log("sters");
  }

  updateItem(id: string) {
    this.updateDialog.open(UpdateDialogComponent, {
      data: {itemId: id}
    });
    this.updateDialog.afterAllClosed.subscribe((next) => this.getAllItems())

  }

  addItem() {
    this.dialog.open(AddDialogComponent);
    this.dialog.afterAllClosed.subscribe((next) => this.getAllItems())
  }

  nrOfColumns(): number {
    return 4;
  }

  openPhoto(url) {
    window.open(url, null)
  }
}
