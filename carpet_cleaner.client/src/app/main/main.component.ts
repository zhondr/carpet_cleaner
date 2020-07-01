import { Component } from '@angular/core';
import {NbMenuItem} from "@nebular/theme";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-main',
  styleUrls: ['main.component.scss'],
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})


export class MainComponent {

  MENU_ITEMS: NbMenuItem[] = [
    {
      title: "Order",
      icon: 'grid-outline',
      link: '/order',
      home: true
    }
  ];

  menu = this.MENU_ITEMS;
}
