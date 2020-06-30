import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-header-text',
  templateUrl: './header-text.component.html',
  styleUrls: ['./header-text.component.scss']
})
export class HeaderTextComponent implements OnInit {
  @Input() text: string;
  @Input() needLogout: boolean = false;
  @Output("logout") logoutEmitter = new EventEmitter();

  constructor() {

  }

  ngOnInit() {
  }

  logout() {
    this.logoutEmitter.emit();
  }
}
