import { Injectable } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ToastMessegesComponent} from "./toast-messeges.component";

@Injectable({
  providedIn: 'root'
})
export class ToastMessegesService {

  constructor(private snackBar: MatSnackBar) { }
  showToast(displayMessage: string, messageType: 'error' | 'warning' | 'info') {
    this.snackBar.openFromComponent(ToastMessegesComponent, {
      data: {
        message: displayMessage,
        type: messageType
      },
      duration: 4000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: messageType
    });
  }
}
