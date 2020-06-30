import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {SessionService} from '@services/session.service';
import {ApiTest} from "./implementations/api/apiTest";
import {ApiService} from "@services/api.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  constructor(
    private router: Router,
    private translate: TranslateService,
    private session: SessionService,
    private apiService: ApiService) {
    // Устанавливаем язык по умолчанию
    translate.setDefaultLang('ru');
    this.session.registerCulture('ru-KZ');
  }

  ngOnInit() {
    // this.apiService.api = new ApiProd(this.http);
    this.apiService.api = new ApiTest();
  }

  ngOnDestroy(): void {
  }

  logout() {
    this.router.navigate(['/login']);
  }
}
