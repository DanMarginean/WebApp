import { Injectable } from '@angular/core';
import {ItemcardComponent} from "./itemcard/itemcard.component";
import {ItemCardService} from "./itemcard/item-card.service";
import {ItemCard} from "./models/itemCard";

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {

  items:ItemCard[];

  constructor(private item:ItemCardService) { }

  refreshItems() : ItemCard[]{
    this.item.getAllItemCards().subscribe(item =>{
      this.items=item;
    });
    return this.items;
  }
}
