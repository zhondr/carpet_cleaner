import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login.component';
import {LoginRoutingModule} from "@pages/login/login.routing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ComponentsModule} from "@components/components.module";
import {TranslateModule} from "@ngx-translate/core";


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    TranslateModule,
    LoginRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    ComponentsModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LoginModule {
}
