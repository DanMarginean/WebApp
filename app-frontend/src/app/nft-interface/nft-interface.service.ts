import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
export interface Nft{
  id:string,
  floor_price:string,
  total_volume:string,
  total_supply:string,
  seven_day_sales:string,
  num_owners:string,
  name:string,
  date?:Date
}

@Injectable({
  providedIn: 'root'
})
export class NftInterfaceService {
url=environment.apiUrlNft;
enpoint=environment.apiNftEndpoints.nft;
endpointdelete=environment.apiNftEndpoints.deletenft;
  constructor(private http:HttpClient) { }

  getAllNfts() :Observable<Nft[]> {
    return this.http.get<Nft[]>(this.url + this.enpoint);
  }
  deleteById(id:string):Observable<null>{
    return this.http.delete<null>(this.url+this.endpointdelete+id);

  }
}
