import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ItemAdd} from "../models/itemAdd";
import {ItemCard} from "../models/itemCard";
import {FilterParams} from "../models/filterParams";

@Injectable({
  providedIn: 'root'
})
export class ItemCardService implements OnInit {
  url = environment.apiURL;
  itemcardEndpoint = environment.apiEndpoints.itemcard;
  deleteCardEndpoint = environment.apiEndpoints.deleteCard;
  updateCardEndpoint = environment.apiEndpoints.updateCard;
  searchEndpoint = environment.apiEndpoints.search;

  oneItemEndpoint = environment.apiEndpoints.getItem;
  checkUpdate = false;

  constructor(private http: HttpClient,
  ) {
  }

  ngOnInit(): void {
    // this.getAllItemCards();
  }

  getAllItemCards(): Observable<ItemCard[]> {
    return this.http.get<ItemCard[]>(this.url + this.itemcardEndpoint);
  }

  deleteItemById(itemId: string) {
    return this.http.delete(this.url + this.deleteCardEndpoint + itemId);

  }

  updateById(itemId: string, item: ItemAdd) {
    return this.http.put(this.url + this.updateCardEndpoint + itemId, item);

  }

  filterItems(params : FilterParams) {
    return this.http.post<ItemCard[]>(this.url+this.searchEndpoint,params)

  }

  getItemById(id):Observable<ItemAdd>{
    return this.http.get<ItemAdd>(this.url+this.oneItemEndpoint +id);
  }
}
