import { Injectable } from '@angular/core';
import {ItemAdd} from "../models/itemAdd";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UpdateDialogService {
url=environment.apiURL;
endpoint=environment.apiEndpoints.updateCard;
  constructor(private http:HttpClient) { }

  updateItemS(itemId:string,item:ItemAdd):Observable<ItemAdd>{
    return this.http.put<ItemAdd>(this.url+this.endpoint+itemId,item);
  }
}
