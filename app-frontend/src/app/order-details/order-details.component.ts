import {Component, OnInit} from '@angular/core';
import {Cart} from "../models/Cart";
import {MatTableDataSource} from "@angular/material/table";
import {OrderDetailsService} from "./order-details.service";
import {OrderDetail} from "../models/orderDetail";
import {FormControl, FormGroup} from "@angular/forms";
import {UserOrder} from "../models/userOrder";
import {PlaceOrderDetail} from "../models/placeOrderDetail";
import {round} from "@popperjs/core/lib/utils/math";

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

  final_price: number = 0; // sa il adaug in orderDetailDTO si sa il calculez in back
  shipping:number;

  orderForm = new FormGroup({
    orderFirstName: new FormControl(''),
    orderLastName: new FormControl(''),
    orderEmail: new FormControl(''),
    contactNumber: new FormControl(''),
    orderAdress: new FormControl(''),
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
        this.userOrder=this.orderDetails[0].userDTO;
        // console.log(this.userOrder);
        this.orderDetails.map(orderDetail =>{
          this.totalCart += orderDetail.quantity *(parseInt(orderDetail.item.price) - ((parseInt(orderDetail.item.percentSale) / 100) * parseInt(orderDetail.item.price)));
          orderDetail.price=orderDetail.quantity *(parseInt(orderDetail.item.price) - ((parseInt(orderDetail.item.percentSale) / 100) * parseInt(orderDetail.item.price)));
        })
        if(this.totalCart >=150){
          this.shipping=0;
        }
        else{this.shipping=15;
          this.totalCart+=this.shipping;}

        this.totalCart = parseFloat(this.totalCart.toFixed(2));

        this.orderForm.patchValue(
          {
            orderFirstName: this.userOrder.orderFirstName,
            orderLastName: this.userOrder.orderLastName,
            orderEmail: this.userOrder.orderEmail,
            orderAdress: this.userOrder.orderAdress,
            contactNumber: this.userOrder.contactNumber
          });
      },
      (error)=>{})
  }

  placeOrder(){
    this.userOrder.orderEmail=this.orderForm.value.orderEmail;
    this.userOrder.orderFirstName = this.orderForm.value.orderFirstName;
    this.userOrder.orderLastName = this.orderForm.value.orderLastName;
    this.userOrder.orderAdress = this.orderForm.value.orderAdress;
    this.userOrder.contactNumber = this.orderForm.value.contactNumber;


    this.orderDetailsService.placeOrder(this.userOrder).subscribe((response)=>{console.log(response)},
      (error)=>{console.log(error)})
  }

  isFreeShipping():boolean{
    if(this.totalCart>=300){return true;}
    else {return false;}
      }
}
