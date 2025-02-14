import {forwardRef, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import {MatIconModule} from "@angular/material/icon";
import { HomepageComponent } from './homepage/homepage.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import { ProductListComponent } from './product-list/product-list.component';
import {MatGridListModule} from "@angular/material/grid-list";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import { ItemcardComponent } from './itemcard/itemcard.component';
import {MatCardModule} from "@angular/material/card";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule, NG_VALUE_ACCESSOR, ReactiveFormsModule} from "@angular/forms";
import {MatDividerModule} from "@angular/material/divider";
import { NftInterfaceComponent } from './nft-interface/nft-interface.component';
import { AddDialogComponent } from './add-dialog/add-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { ErrorComponent } from './error/error.component';
import {MatMenuModule} from "@angular/material/menu";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ToastMessegesComponent } from './toast-messeges/toast-messeges.component';
import {AuthInterceptorService} from "./authentication/auth.interceptor.service";
import {AuthService} from "./authentication/auth.service";
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {MatSnackBar} from "@angular/material/snack-bar";
import {RegisterInterceptor} from "./authentication/register.interceptor";
import {NgbCarousel, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { MainComponent } from './main/main.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatListModule } from '@angular/material/list';
import { UpdateDialogComponent } from './update-dialog/update-dialog.component';
import { ItemDetailsComponent } from './item-details/item-details.component';
import { CartComponent } from './cart/cart.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import { OrderDetailsComponent } from './order-details/order-details.component';



@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    ProductListComponent,
    ItemcardComponent,
    NftInterfaceComponent,
    AddDialogComponent,
    ErrorComponent,
    LoginComponent,
    RegisterComponent,
    ToastMessegesComponent,
    MainComponent,
    UpdateDialogComponent,
    ItemDetailsComponent,
    CartComponent,
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatIconModule,
    MatSidenavModule,
    MatGridListModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    HttpClientModule,
    FormsModule,
    MatDividerModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatMenuModule,
    NgbModule,
    LayoutModule,
    MatListModule,
    MatPaginatorModule,
    MatTableModule
  ],
  providers: [
    AuthService,
    JwtHelperService,
    MatSnackBar,
    NgbCarousel,
    ItemcardComponent,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    {provide: HTTP_INTERCEPTORS,
              useClass: AuthInterceptorService,
              multi: true
    },
    {provide:HTTP_INTERCEPTORS,
    useClass: RegisterInterceptor,
    multi:true},
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ItemcardComponent),
      multi: true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
