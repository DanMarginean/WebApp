import {ItemAdd} from "./itemAdd";

export interface Cart{
  id:string;
  item:ItemAdd;
  quantity:number
  total:number;
}
