import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AlertComponent} from "@components/alert/alert.component";
import {MatTabsModule} from "@angular/material/tabs";
import {MatIconModule} from "@angular/material/icon";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";
import { DividerBlockComponent } from './divider-block/divider-block.component';
import { HeaderTextComponent } from './header-text/header-text.component';
import {TranslateModule} from "@ngx-translate/core";


const COMPONENTS = [
  AlertComponent,
  DividerBlockComponent,
  HeaderTextComponent
];


@NgModule({
  declarations: [COMPONENTS],
  exports: [COMPONENTS],
  imports: [
    CommonModule,
    MatTabsModule,
    MatIconModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    TranslateModule,
    MatSelectModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComponentsModule {
}
