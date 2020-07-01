import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LocalDatePipe} from './local-date.pipe';


const PIPES = [
  LocalDatePipe
];


@NgModule({
  declarations: [PIPES],
  exports: [PIPES],
  imports: [
    CommonModule
  ]
})
export class PipesModule {
}
