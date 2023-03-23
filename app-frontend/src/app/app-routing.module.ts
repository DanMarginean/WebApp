import { NgModule } from '@angular/core';
import {ActivatedRoute, RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./homepage/homepage.component";
import {ProductListComponent} from "./product-list/product-list.component";
import {AppComponent} from "./app.component";
import {NftInterfaceComponent} from "./nft-interface/nft-interface.component";
import {ErrorComponent} from "./error/error.component";
import {ItemcardComponent} from "./itemcard/itemcard.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuardService} from "./authentication/auth-guard.service";
import {MainComponent} from "./main/main.component";
import {ItemDetailsComponent} from "./item-details/item-details.component";
import {CartComponent} from "./cart/cart.component";


const routes: Routes = [
  {path: '', redirectTo:'main',pathMatch:'full'}, //pentru routelinkActive daca un childPath ii active si parentPath ii active
  // children: [
    {path:'home', component: HomepageComponent,
    },

  { path: 'main', component: MainComponent,
    children:[
      { path: 'items', component: ItemcardComponent},
      {path:'items/:id', component: ItemcardComponent,
      },
      {path:'item-detail',component:ItemDetailsComponent}
    ]},
  {path:'cart',component:CartComponent},
   {path:'profile', component:ErrorComponent, //ProfileComponent,
  canActivate:[AuthGuardService]},
    {path:'login', component: LoginComponent },
    {path:'register', component: RegisterComponent },
    {path:'**', component: ErrorComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
