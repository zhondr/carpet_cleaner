import {Injectable} from '@angular/core';
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import localeKz from '@angular/common/locales/kk';

@Injectable({providedIn: 'root'})
export class SessionService {
  private _localeFull: string;

  get localeFull() {
    return this._localeFull || 'ru-KZ';
  }

  set localeFull(value: string) {
    this._localeFull = value;
  }

  get locale() {
    return this._localeFull.split('-')[0];
  }

  registerCulture(culture: string) {
    if (!culture) {
      return;
    }

    this.registerLocaleData(culture);
    localStorage.setItem('locale', this.localeFull);
  }

  subscribe(callback: (event: StorageEvent) => any) {
    window.addEventListener("storage", event => {
      if (event.key != 'locale') {
        return;
      }

      this.registerLocaleData(event.newValue);
      callback(event);
    });
  }

  private registerLocaleData(culture: string) {
    this.localeFull = culture;

    // Register locale data since only the en-US locale data comes with Angular
    switch (culture) {
      case 'ru-KZ':
      case 'ru-RU': {
        registerLocaleData(localeRu);
        break;
      }
      case 'kk-KZ': {
        registerLocaleData(localeKz);
        break;
      }
    }
  }
}
