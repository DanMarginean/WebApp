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
  //rute shop=home, profile, cart, favorites
  // shop rutes: freshwater,marine,ponds,forum,about us
  // profile routes: comenzi, contul meu,
  // cart routes: view, command
  //   {path:'/nft',component:NftInterfaceComponent}
  // ]
  // }
  // {path: 'homepage', component: HomepageComponent},
  // {path: '', component: HomepageComponent},
  // {path: 'product-list', component: ProductListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
