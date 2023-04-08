// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiURL:"http://localhost:8081/api/",
  apiEndpoints:{
    itemcard:"cards",
    addProduct:"items",
    deleteCard:"delete/",
    updateCard:"update/",
    search:"search",
    addCart:"addCart/",
    cartDetails:"getCartDetails",
    deleteCart: "deleteCart/",
    checkout: "checkout",
    getItem: "items/",
    placeOrder: "placeOrder",
    getItemDetails:"itemDetails/",
    getCategory: "category/",
    getGeneralCategory:"generalCategory/",
    getMenuCategory: "menuCategory/",
    getMenuGeneralCategory: "menuGeneralCategory"
  },
  apiURLauth:"http://localhost:8081/api/v1/auth/",
  apiEndpointsauth:{
    register:"register",
    auth:"authenticate"
  },
  apiUrlNft:"http://localhost:8081/api/v1/",
  apiNftEndpoints:{
    nft:"getAll",
    deletenft:"delete/",
    updatenft:"update/"

  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
