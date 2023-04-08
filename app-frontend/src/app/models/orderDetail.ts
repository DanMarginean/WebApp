import {ItemAdd} from "./itemAdd";
import {UserOrder} from "./userOrder";
import {PlaceOrderDetail} from "./placeOrderDetail";

export interface OrderDetail{
  item:ItemAdd;
  userDTO:PlaceOrderDetail;
  price:number;
  quantity:number;
}
