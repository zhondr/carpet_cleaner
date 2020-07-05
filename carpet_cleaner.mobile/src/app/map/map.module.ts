import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {AngularYandexMapsModule} from "angular8-yandex-maps";
import {MapComponent} from "./map.component";



@NgModule({
  declarations: [
    MapComponent
  ],
  imports: [
    CommonModule,
    AngularYandexMapsModule
  ],
  exports: [
    MapComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MapModule { }
