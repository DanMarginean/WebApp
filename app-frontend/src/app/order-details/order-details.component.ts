import {Component, OnInit} from '@angular/core';
import {Cart} from "../models/Cart";
import {MatTableDataSource} from "@angular/material/table";
import {OrderDetailsService} from "./order-details.service";
import {OrderDetail} from "../models/orderDetail";
import {FormControl, FormGroup} from "@angular/forms";
import {UserOrder} from "../models/userOrder";
import {PlaceOrderDetail} from "../models/placeOrderDetail";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit{
  displayedColumns: string[] = ['name', 'brand', 'price', 'serialNumber', 'sale', 'quantity', 'final_price'];
  orderDetails: OrderDetail[] = [];
  dataSource = new MatTableDataSource<Object>(this.orderDetails);
  totalCart: number = 0;
  shipping:number;

  orderForm = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    contactNumber: new FormControl(''),
    adress: new FormControl(''),
  });

  userOrder:PlaceOrderDetail = {
    orderEmail:"",
    orderFirstName: "",
    orderLastName: "",
    orderAdress:"",
    contactNumber: ""
  }


  constructor(private orderDetailsService:OrderDetailsService) {
}

  ngOnInit(): void {
    this.checkout();
  }
checkout(){
   this.orderDetailsService.checkout().subscribe((response:any[]) =>{
     this.orderDetails  = response;
     console.log(this.orderDetails);
     this.orderDetails.map(orderDetail =>{
       this.totalCart += orderDetail.quantity *(parseInt(orderDetail.item.price) - ((parseInt(orderDetail.item.percentSale) / 100) * parseInt(orderDetail.item.price)));
        if(this.totalCart >=150){
          this.shipping=0;
        }
        else{this.shipping=15;}
     })
     },
     (error)=>{})
}

placeOrder(){
    this.userOrder.orderEmail=this.orderForm.value.email;
    this.userOrder.orderFirstName = this.orderForm.value.firstname;
    this.userOrder.orderLastName = this.orderForm.value.lastname;
    this.userOrder.orderAdress = this.orderForm.value.adress;
    this.userOrder.contactNumber = this.orderForm.value.contactNumber;


this.orderDetailsService.placeOrder(this.userOrder).subscribe((response)=>{console.log(response)},
  (error)=>{console.log(error)})
}

  isFreeShipping():boolean{
    if(this.totalCart>=300){return true;}
    else {return false;}
      }
}
