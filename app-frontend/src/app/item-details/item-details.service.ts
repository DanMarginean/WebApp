import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ItemDetails} from "../models/itemDetails";

@Injectable({
  providedIn: 'root'
})
export class ItemDetailsService {
   url = environment.apiURL;
  endpointGetItem = environment.apiEndpoints.getItemDetails;

  constructor(private http:HttpClient) { }

  getItemDetailsById(id):Observable<ItemDetails>{
    return this.http.get<ItemDetails>(this.url + this.endpointGetItem+id);
  }
}
