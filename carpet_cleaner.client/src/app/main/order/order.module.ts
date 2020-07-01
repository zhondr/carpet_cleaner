import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { NbCardModule, NbIconModule, NbInputModule, NbTreeGridModule } from '@nebular/theme';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { ThemeModule } from '../../@theme/theme.module';
import {OrderComponent} from "./order.component";
import {OrderRoutingModule} from "./order-routing.module";
import {TranslateModule} from "@ngx-translate/core";

@NgModule({
  imports: [
    TranslateModule,
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
    ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class OrderModule { }
