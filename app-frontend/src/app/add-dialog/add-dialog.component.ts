import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {DialogService} from "./dialog.service";
import {ItemAdd} from "../models/itemAdd";
import {ItemcardComponent} from "../itemcard/itemcard.component";
import {Router} from "@angular/router";
import {ToastMessegesService} from "../toast-messeges/toast-messeges.service";
import {ImageFile} from "../models/image-file";
import {DomSanitizer} from "@angular/platform-browser";
import {parseJson} from "@angular/cli/src/utilities/json-file";

@Component({
  selector: 'app-add-dialog',
  templateUrl: './add-dialog.component.html',
  styleUrls: ['./add-dialog.component.css']
})
export class AddDialogComponent implements OnInit {
  addButton = false;


  addItemForm = new FormGroup({
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
    quantity: "",
    percentSale: "",
    image: {file:null,
            url:''}
  }

  selectedFile: File;
  imageUrl: string;

  imageFile:ImageFile;

  constructor(public dialogRef: MatDialogRef<AddDialogComponent>,
              private dialService: DialogService,
              private router: Router,
              private toast: ToastMessegesService,
              private items: ItemcardComponent,
              private sanitizer: DomSanitizer
  ) {

  }

  ngOnInit(): void {
    // console.log(this.imageFile);

  }


  addItem() {
    this.item.brand = this.addItemForm.value.brand;
    this.item.name = this.addItemForm.value.name;
    this.item.serialNumber = this.addItemForm.value.serialNumber;
    this.item.price = this.addItemForm.value.price;
    this.item.generalCategory = this.addItemForm.value.generalCategory;
    this.item.category = this.addItemForm.value.category;
    this.item.descriere = this.addItemForm.value.descriere;
    this.item.quantity = this.addItemForm.value.quantity;
    this.item.percentSale = this.addItemForm.value.percentageSale;
    this.item.image=this.imageFile;
    console.log(this.imageFile);
    console.log(this.item);
    const itemFormData = this.createFormData(this.item)
    console.log(itemFormData);
    this.dialService.addItem(itemFormData).subscribe(response => {
      this.dialogRef.close();
      this.toast.showToast("Item succesfully created", "info");
      console.log("item added")
      console.log(response);
    },
      (error)=>{console.log(error)});

  }

  createFormData(item: ItemAdd): FormData {
    const formData = new FormData();
    formData.append('item',
      // JSON.stringify(this.item));
      new Blob([JSON.stringify(item)], {type: 'application/json'}));
    // for (var image = 0; image < item.image.length; image++) {
      formData.append('image',
        item.image.file, //[image]
        item.image.file.name)//[image]
    // }
    return formData;
  }

  onFileSelected(event) {
    console.log(event);
    if (event.target.files) {
      this.selectedFile = event.target.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.imageUrl = reader.result as string;
// console.log(this.imageUrl);
        this.imageFile = {
          file: this.selectedFile,
          url: this.sanitizer.bypassSecurityTrustUrl(this.imageUrl)
        }
        // console.log(this.imageFile);
      };

      reader.readAsDataURL(this.selectedFile);
    }
  }
}
