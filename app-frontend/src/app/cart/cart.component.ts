import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CartService} from "./cart.service";
import {Cart} from "../models/Cart";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {ItemAdd} from "../models/itemAdd";


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['name', 'brand', 'price', 'serialNumber', 'sale', 'quantity', 'actions', 'final_price'];
  cartDetails: Cart[] = [];


  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Cart>(this.cartDetails);
  totalCart: number = 0;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.getCartDetails();
    // this.calculateTotal();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  getCartDetails() {
    this.cartService.getCartDetails().subscribe(
      (response: any[]) => {
        console.log(response);
        this.cartDetails = response;
        const myRegex: RegExp = /^\d+$/;
        this.cartDetails.map(cartDetail => {
          if (myRegex.test(cartDetail.item.percentSale) === null) {
            cartDetail.item.percentSale = "0"
          }
          if (cartDetail.quantity === null) {
            cartDetail.quantity = 1
          }
          cartDetail.total = parseInt(cartDetail.item.price) * cartDetail.quantity - ((parseInt(cartDetail.item.percentSale) / 100) * parseInt(cartDetail.item.price));
          this.totalCart += cartDetail.total;
          //ADD TOTAL FIELD IN CART ENTITY BACKEND AND MAKE THIS LOGIC
        })
      },
      (error) => {
        console.log(error);
      });
  }

  deleteCartDetails(id: string) {
    this.cartService.deleteCartDetails(id).subscribe(response => {
        console.log(response)
        this.getCartDetails();
      },
      error => {
        console.log(error)
      })
  }

  checkout() {

  }

}
