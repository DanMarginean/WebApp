import {ImageFile} from "./image-file";
import {SafeUrl} from "@angular/platform-browser";

export interface ItemCard{
  id:string;
  name:string;
  price:string;
  descriere:string;
  filePath:SafeUrl;
  bytes:string;
  image:File;
}
