import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {
  NbButtonModule,
  NbCardModule,
  NbIconModule,
  NbInputModule,
  NbListModule,
  NbTreeGridModule
} from '@nebular/theme';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { ThemeModule } from '../../@theme/theme.module';
import {OrderComponent} from "./order.component";
import {OrderRoutingModule} from "./order-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {OrderDialogComponent} from "./modal/order-dialog.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    TranslateModule,
    FormsModule,
    NbListModule,
    NbCardModule,
    NbIconModule,
    NbButtonModule,
    NbInputModule,
    ThemeModule,
    OrderRoutingModule,
    Ng2SmartTableModule,
  ],
  declarations:
    [
      OrderComponent,
      OrderDialogComponent
    ],
  entryComponents: [
    OrderDialogComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class OrderModule { }
