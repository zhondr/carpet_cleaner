import { NgModule } from '@angular/core';
import { NbCardModule, NbIconModule, NbInputModule, NbTreeGridModule } from '@nebular/theme';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { ThemeModule } from '../../@theme/theme.module';
import {OrderComponent} from "./order.component";
import {OrderRoutingModule} from "./order-routing.module";

@NgModule({
  imports: [
    NbCardModule,
    NbTreeGridModule,
    NbIconModule,
    NbInputModule,
    ThemeModule,
    OrderRoutingModule,
    Ng2SmartTableModule,
  ],
  declarations:
    [
      OrderComponent
    ]
})
export class OrderModule { }
