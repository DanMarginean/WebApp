import {Injectable} from '@angular/core';
import {ItemcardComponent} from "./itemcard/itemcard.component";

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {

  constructor(private item: ItemcardComponent) {
  }

  refreshItems() {
    this.item.getAllItems();
  }
}
