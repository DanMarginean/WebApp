import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from "./error/error.component";
import {ItemcardComponent} from "./itemcard/itemcard.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuardService} from "./authentication/auth-guard.service";
import {MainComponent} from "./main/main.component";
import {ItemDetailsComponent} from "./item-details/item-details.component";
import {CartComponent} from "./cart/cart.component";


const routes: Routes = [
  {path: '', redirectTo: 'main', pathMatch: 'full'},

  {
    path: 'main', component: MainComponent,
    children: [
      {path: 'items', component: ItemcardComponent},
      {
        path: 'items/:id', component: ItemcardComponent,
      },
      {path: 'item-detail', component: ItemDetailsComponent}
    ]
  },
  {path: 'cart', component: CartComponent},
  {
    path: 'profile', component: ErrorComponent,
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
