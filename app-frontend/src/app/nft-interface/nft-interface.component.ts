import { Component, OnInit } from '@angular/core';
import {Nft, NftInterfaceService} from "./nft-interface.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {AddDialogComponent} from "../add-dialog/add-dialog.component";

@Component({
  selector: 'app-nft-interface',
  templateUrl: './nft-interface.component.html',
  styleUrls: ['./nft-interface.component.css']
})
export class NftInterfaceComponent implements OnInit {
  ngOnInit(): void {
this.getAllItems();

  }
  nfts:Nft[]=[];
  nft:Nft={
    id:"",
    floor_price:"",
    total_volume:"",
    total_supply:"",
    seven_day_sales:"",
    num_owners:"",
    date:new Date(),
    name:""
   }


  constructor(private nftService:NftInterfaceService,
              private dialog :MatDialog) { }

  getAllItems():void{
    this.nftService.getAllNfts().subscribe(nfts =>{
      console.log(nfts)
      this.nfts=nfts});

  }
deleteNft(id:string):void{

    this.nftService.deleteById(id).subscribe(() => {
      console.log(this.nft.id)
      this.getAllItems();
    })
  }

  openDialog(){
const dialogConfig = new MatDialogConfig();

dialogConfig.disableClose = true;
dialogConfig.autoFocus = true;
    dialogConfig.data = {
      id:this.nft.id,
      floor_price:this.nft.floor_price,
      total_volume:this.nft.total_volume,
      total_supply:this.nft.total_supply,
      seven_day_sales:this.nft.seven_day_sales,
      num_owners:this.nft.num_owners,
      date:new Date(),
      name:this.nft.name
    }
this.dialog.open(AddDialogComponent,dialogConfig);
  }

}
