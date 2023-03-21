import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ItemAdd} from "../models/itemAdd";
import {ItemCard} from "../models/itemCard";
import {DomSanitizer} from "@angular/platform-browser";
import {ImageFile} from "../models/image-file";

@Injectable({
  providedIn: 'root'
})
export class ItemCardService implements OnInit{
  url=environment.apiURL;
  itemcardEndpoint=environment.apiEndpoints.itemcard;
  deleteCardEndpoint=environment.apiEndpoints.deleteCard;
  updateCardEndpoint=environment.apiEndpoints.updateCard;
  checkUpdate=false;
  constructor(private http:HttpClient,
              // private sanitizer: DomSanitizer
  ) {
  }

  ngOnInit(): void {
    this.getAllItemCards();
  }

  getAllItemCards () : Observable<ItemCard[]>{
    return this.http.get<ItemCard[]>(this.url+this.itemcardEndpoint);
  }
  deleteItemById(itemId:string) {
    return this.http.delete(this.url+this.deleteCardEndpoint+itemId);

  }
  updateById(itemId:string,item:ItemAdd){
    return this.http.put(this.url+this.updateCardEndpoint+itemId,item);

}

// createImage(itemCard:ItemCard){
    // const itemImage : any = itemCard.image;
    // const imageBytes : any = itemCard.bytes;

//     const imageBlob = this.bytesToBlob(itemCard.bytes,itemCard.image.type)
//     const imageFile = new File([imageBlob],itemCard.image.name,{type: itemCard.image.type});
//     // const finalImage: ImageFile = {
//     //   file: imageFile,
//     //   url:this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
//     // }
// const url= this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
//
//     return url;
//   const blob = new Blob([]);
//   }

// bytesToBlob(bytes,imageType){
//     const byteString = window.atob(bytes);
//     const arrayBuffer = new ArrayBuffer(byteString.length);
//     const int8Array = new Uint8Array(arrayBuffer);
//
//     for (let i = 0;i<byteString.length;i++){
//      int8Array[i] = byteString.charCodeAt(i);
//     }
//
//     const blob = new Blob([int8Array],{type:imageType});
//     return blob;
// }
// checkUpdateClick(){
//     return this.checkUpdate=;
// }
// setUpdate(state:boolean){
//     this.checkUpdate=state;
// }
}
