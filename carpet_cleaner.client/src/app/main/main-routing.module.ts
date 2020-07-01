import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { MainComponent } from './main.component';
import {NotFoundComponent} from "../example_pages/miscellaneous/not-found/not-found.component";

const routes: Routes = [{
  path: '',
  component: MainComponent,
  children: [
    {
      path: '',
      redirectTo: '/order'
    },
    {
      path: 'order',
      loadChildren: () => import("./order/order.module")
        .then(m => m.OrderModule),
    },
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {
}
