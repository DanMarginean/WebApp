import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Nft} from "../nft-interface/nft-interface.service";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {ItemAdd} from "../models/itemAdd";

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  url = environment.apiURL;
  endpoint = environment.apiEndpoints.addProduct;

  constructor(private http: HttpClient) {
  }

  updateByID(id: string, nft: Nft): Observable<Object> {
    return this.http.put(this.url + this.endpoint + id, nft);
  }

  addItem(item: FormData): Observable<any> { //itemAdd
    console.log("ad");
    return this.http.post<ItemAdd>("http://localhost:8081/api/items", item);
  }

  updateItemS(itemId: string, item: ItemAdd): Observable<ItemAdd> {
    return this.http.put<ItemAdd>(this.url + this.endpoint + itemId, item);
  }
}
