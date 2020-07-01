import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import {OrderComponent} from "./order.component";
import {NotFoundComponent} from "../../example_pages/miscellaneous/not-found/not-found.component";

const routes: Routes = [
  {
    path: '',
    component: OrderComponent,
  },
  {
    path: '**',
    component: NotFoundComponent,
  }
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrderRoutingModule {
}
