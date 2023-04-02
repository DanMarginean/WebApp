import {ImageFile} from "./image-file";


export interface ItemAdd{
  brand:string;
  name:string;
  serialNumber:string;
  price:string;
  generalCategory:string;
  category:string;
  descriere:string;
  quantity:string;
  percentSale:string
  image:ImageFile[];
}
