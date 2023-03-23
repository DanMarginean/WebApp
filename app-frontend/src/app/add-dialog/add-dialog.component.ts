import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Nft, NftInterfaceService} from "../nft-interface/nft-interface.service";
import {DialogService} from "./dialog.service";
import {Observable} from "rxjs";
import { ItemAdd} from "../models/itemAdd";
import {MainComponent} from "../main/main.component";
import {ItemcardComponent} from "../itemcard/itemcard.component";
import {ItemCardService} from "../itemcard/item-card.service";
import {CommunicationService} from "../communication.service";
import {Router} from "@angular/router";
import {ToastMessegesService} from "../toast-messeges/toast-messeges.service";
import {computeStartOfLinePositions} from "@angular/compiler-cli/src/ngtsc/sourcemaps/src/source_file";
import {ImageFile} from "../models/image-file";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-add-dialog',
  templateUrl: './add-dialog.component.html',
  styleUrls: ['./add-dialog.component.css']
})
export class AddDialogComponent implements OnInit {
addButton=false;


  addItemForm =new FormGroup({
    brand: new FormControl(''),
    name: new FormControl(''),
    serialNumber: new FormControl(''),
    price: new FormControl(''),
    generalCategory: new FormControl(''),
    category: new FormControl(''),
    descriere: new FormControl(''),
    quantity: new FormControl(''),
    percentageSale: new FormControl(''),
    image:new FormControl('')
  });
  item:ItemAdd={
    brand:"",
    name:"",
    serialNumber:"",
    price:"",
    generalCategory:"",
    category:"",
    descriere:"",
    quantity:"",
    percentSale:"",
    image:[]
  }


  constructor(public dialogRef: MatDialogRef<AddDialogComponent>,
              private dialService:DialogService,
              private router:Router,
              private toast:ToastMessegesService,
              private items:ItemcardComponent,
              private sanitizer:DomSanitizer
             ) {

  }

  ngOnInit(): void {

  }
// updateItem(id:string):void{
//     this.nftservice.getAllNfts()
//     this.dialService.updateByID(id,this.nft).subscribe(() => {
//       console.log(this.nft.id)
//       // this.getAllItems();
//       this.dialogRef.close(true)
//     })
//     // this.http.put(environment.apiUrlNft+environment.apiNftEndpoints.updatenft+this.data.id,this.form.value)
// }

  addItem(){
     this.item.brand=this.addItemForm.value.brand;
     this.item.name=this.addItemForm.value.name;
     this.item.serialNumber=this.addItemForm.value.serialNumber;
     this.item.price=this.addItemForm.value.price;
     this.item.generalCategory=this.addItemForm.value.generalCategory;
     this.item.category=this.addItemForm.value.category;
     this.item.descriere=this.addItemForm.value.descriere;
     this.item.quantity=this.addItemForm.value.quantity;
     this.item.percentSale=this.addItemForm.value.percentageSale;
     const itemFormData = this.createFormData(this.item)
    this.dialService.addItem(itemFormData).subscribe(response=>{
      this.dialogRef.close();
      this.toast.showToast("Item succesfully created","info");
      console.log("item added")
      console.log(response);
    });
    // this.items.getAllItems();
  }

  createFormData(item:ItemAdd) :FormData{
    const formData = new FormData();
    formData.append('item',
      new Blob([JSON.stringify(item)],{type: 'application/json'}));
    for(var image = 0; image<item.image.length;image++){
    formData.append('image',
      item.image[image].file,
      item.image[image].file.name)
    }
    return formData;
  }

  onFileSelected(event){
    console.log(event);
    if(event.target.files){
      const files = event.target.files[0];
      const imageFile:ImageFile = {
        file:files,
        url: this.sanitizer.bypassSecurityTrustUrl(
          //creeaza un url din fisieru asta
          window.URL.createObjectURL(files)
        )
      }
      this.item.image.push(imageFile)
    }
  }
}
