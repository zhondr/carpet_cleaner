import { NgModule } from '@angular/core';
import { NbMenuModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { MainComponent } from './main.component';
import { MainRoutingModule } from './main-routing.module';
import {DashboardModule} from "../example_pages/dashboard/dashboard.module";
import {ECommerceModule} from "../example_pages/e-commerce/e-commerce.module";
import {MiscellaneousModule} from "../example_pages/miscellaneous/miscellaneous.module";

@NgModule({
  imports: [
    MainRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    ECommerceModule,
    MiscellaneousModule,
  ],
  declarations: [
    MainComponent,
  ],
})
export class MainModule {
}
