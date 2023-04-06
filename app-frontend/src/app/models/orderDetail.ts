import {ItemAdd} from "./itemAdd";
import {UserOrder} from "./userOrder";

export interface OrderDetail{
  item:ItemAdd;
  user:UserOrder;
  price:number;
  quantity:number;
}
