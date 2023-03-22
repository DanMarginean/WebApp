import {Component, Inject, OnInit} from '@angular/core';
import {MAT_SNACK_BAR_DATA, MatSnackBarRef} from "@angular/material/snack-bar";

@Component({
  selector: 'app-toast-messeges',
  templateUrl: './toast-messeges.component.html',
  styleUrls: ['./toast-messeges.component.css']
})
export class ToastMessegesComponent implements OnInit {

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any,
              public snackBarRef: MatSnackBarRef<ToastMessegesComponent>) {
  }

  ngOnInit(): void {
  }

}
