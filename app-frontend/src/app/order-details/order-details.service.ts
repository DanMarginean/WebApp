import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderDetailsService {
url = environment.apiURL;
endpointCheckout = environment.apiEndpoints.checkout;
placeOrderEndpoint = environment.apiEndpoints.placeOrder;
  constructor( private http:HttpClient) { }

  checkout(){
    return this.http.get(this.url+this.endpointCheckout);
  }
  placeOrder(orderDetail){
    return this.http.post(this.url+this.placeOrderEndpoint,orderDetail);
  }


}
