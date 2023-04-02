import {SafeUrl} from "@angular/platform-browser";

export interface ItemCard {
  id: string;
  name: string;
  price: string;
  descriere: string;
  filePath: SafeUrl;
  quantity:number;
  bytes: string;
  image: File;
}
