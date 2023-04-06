import {ImageFile} from "./image-file";
import {SafeUrl} from "@angular/platform-browser";

export interface ItemDetails{
  brand:string;
  name:string;
  serialNumber:string;
  price:string;
  generalCategory:string;
  category:string;
  descriere:string;
  quantity:string;
  percentSale:string;
  // filePath: SafeUrl;
  bytes:string;
  image: File;

}
