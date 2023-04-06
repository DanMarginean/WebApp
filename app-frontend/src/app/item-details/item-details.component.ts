import {Component, Inject, Input, OnInit} from '@angular/core';
import {ItemDetailsService} from "./item-details.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ItemDetails} from "../models/itemDetails";
import {ActivatedRoute} from "@angular/router";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {ItemCard} from "../models/itemCard";
import {ItemCardService} from "../itemcard/item-card.service";
import {ItemAdd} from "../models/itemAdd";

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent  implements OnInit{

  itemId:string;
  itemDetails:ItemDetails

  constructor(private itemDetailsService : ItemDetailsService,
              private route: ActivatedRoute,
              private sanitizer: DomSanitizer,
              private itemCardService:ItemCardService) {
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params =>{
      this.itemId = params.get('detailId');
      console.log(this.itemId);
      //ToDo  add parameter to getItemDetails and use it here
    })
    this.getItemDetails();
    // console.log(this.itemDetails);
  }

  getItemDetails(){
    this.itemDetailsService.getItemDetailsById(this.itemId).subscribe((response)=>{
      this.itemDetails=response;
      console.log(response)
      },
      (error)=>{console.log(error)})
}

  getImageUrl(imageBytes: string): SafeUrl {
    const byteCharacters = atob(imageBytes);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    const blob = new Blob([byteArray], {type: 'image/jpeg'});
    const imageUrl = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }


}
