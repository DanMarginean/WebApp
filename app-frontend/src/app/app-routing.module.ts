import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from "./homepage/homepage.component";
import {ErrorComponent} from "./error/error.component";
import {ItemcardComponent} from "./itemcard/itemcard.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuardService} from "./authentication/auth-guard.service";
import {MainComponent} from "./main/main.component";
import {ItemDetailsComponent} from "./item-details/item-details.component";
import {CartComponent} from "./cart/cart.component";
import {OrderDetailsComponent} from "./order-details/order-details.component";


const routes: Routes = [
  {path: '', redirectTo: 'main', pathMatch: 'full'},

  {
    path: 'home', component: HomepageComponent,
  },

  {
    path: 'main', component: MainComponent,
    children: [
      {path: 'items', component: ItemcardComponent},
      {
        path: 'items/:id', component: ItemcardComponent,canActivate:[AuthGuardService]
      },
      {path: 'item-detail', component: ItemDetailsComponent},
      {path: 'cart', component: CartComponent,canActivate:[AuthGuardService]},
      {path:'order', component: OrderDetailsComponent,canActivate:[AuthGuardService]}
    ]
  },

  {
    path: 'profile', component: ErrorComponent,
    children:[
      // {path: 'myOrders'},
      // {path: 'editAccount'},
    ],
    canActivate: [AuthGuardService]
  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '**', component: ErrorComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
