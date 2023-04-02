import {Injectable} from '@angular/core';
import * as http from "http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Cart} from "../models/Cart";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  url = environment.apiURL;
  endpointAdd = environment.apiEndpoints.addCart;
  endpointCartDetails = environment.apiEndpoints.cartDetails;
  endpointDelete = environment.apiEndpoints.deleteCart;

  constructor(private http: HttpClient) {
  }

  addCart(id: string, quantity: number): Observable<Cart> {
    return this.http.get<Cart>(this.url + this.endpointAdd + id + "/" + quantity);
  }

  getCartDetails() {
    return this.http.get(this.url + this.endpointCartDetails);
  }

  deleteCartDetails(id: string) {
    return this.http.delete(this.url + this.endpointDelete + id);

  }
}
