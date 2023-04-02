import {Component, Inject} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ItemAdd} from "../models/itemAdd";
import {UpdateDialogService} from "./update-dialog.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {ToastMessegesService} from "../toast-messeges/toast-messeges.service";

@Component({
  selector: 'app-update-dialog',
  templateUrl: './update-dialog.component.html',
  styleUrls: ['./update-dialog.component.css']
})
export class UpdateDialogComponent {
  updateItemForm = new FormGroup({
    brand: new FormControl(''),
    name: new FormControl(''),
    serialNumber: new FormControl(''),
    price: new FormControl(''),
    generalCategory: new FormControl(''),
    category: new FormControl(''),
    descriere: new FormControl(''),
    quantity: new FormControl(''),
    percentageSale: new FormControl(''),
    image: new FormControl('')
  });
  item: ItemAdd = {
    brand: "",
    name: "",
    serialNumber: "",
    price: "",
    generalCategory: "",
    category: "",
    descriere: "",
    quantity:"",
    percentSale: "",
    image: []
  }
  // @Input()
  // itemId:any;
  constructor(private updateDialService: UpdateDialogService,
              public dialogRef: MatDialogRef<UpdateDialogComponent>,
              private router: Router,
              private toast: ToastMessegesService,
              @Inject(MAT_DIALOG_DATA) private data: { itemId: string }) {
  }


  updateItem() {
    this.item.brand = this.updateItemForm.value.brand;
    this.item.name = this.updateItemForm.value.name;
    this.item.serialNumber = this.updateItemForm.value.serialNumber;
    this.item.price = this.updateItemForm.value.price;
    this.item.generalCategory = this.updateItemForm.value.generalCategory;
    this.item.category = this.updateItemForm.value.category;
    this.item.descriere = this.updateItemForm.value.descriere;
    this.item.quantity = this.updateItemForm.value.quantity;
    this.item.percentSale = this.updateItemForm.value.percentageSale;

    this.updateDialService.updateItemS(this.data.itemId, this.item).subscribe(response => {
      this.dialogRef.close();
      this.toast.showToast("Item succesfully updated", "info");


      console.log("item updated")
      console.log(this.data.itemId + " asta ii idul")
      console.log(response);

    });

  }
}
