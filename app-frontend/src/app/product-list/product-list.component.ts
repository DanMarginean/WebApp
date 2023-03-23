import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
 ceva:string="ceva";
  constructor() { }

  ngOnInit(): void {
  }
 functie():String{
    return this.ceva;
 }
}
