import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from "@angular/forms";
import {ComponentsModule} from "@components/components.module";
import {MainPageComponent} from "@pages/main-page/main-page.component";
import {MainPageRoutingModule} from "@pages/main-page/main-page.routing.module";
import {TranslateModule} from "@ngx-translate/core";


@NgModule({
  declarations: [
    MainPageComponent
  ],
  imports: [
    TranslateModule,
    MainPageRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    ComponentsModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MainPageModule {
}
