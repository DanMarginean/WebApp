import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Nft} from "../nft-interface/nft-interface.service";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {ItemAdd} from "../models/itemAdd";

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  url=environment.apiURL;
  endpoint=environment.apiEndpoints.addProduct;
  constructor(private http:HttpClient) { }

  addItem(item:FormData):Observable<any>{ //itemAdd
    return this.http.post<ItemAdd>(this.url+this.endpoint,item);
  }

}
