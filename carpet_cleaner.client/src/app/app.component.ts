/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { Component, OnInit } from '@angular/core';
import { AnalyticsService } from './@core/utils/analytics.service';
import { SeoService } from './@core/utils/seo.service';
import {SessionService} from "./services/session.service";
import {ApiService} from "./services/api.service";
import {ApiProd} from "./implementations/api/apiProd";
import {HttpClient} from "@angular/common/http";
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit {

  constructor(private analytics: AnalyticsService,
              private seoService: SeoService,
              private translate: TranslateService,
              private session: SessionService,
              private http: HttpClient,
              private apiService: ApiService) {

    translate.setDefaultLang('ru');
    this.session.registerCulture('ru-KZ');
    this.session.registerCulture('kz-KZ');
  }

  ngOnInit(): void {
    this.analytics.trackPageViews();
    this.seoService.trackCanonicalChanges();
    this.apiService.api = new ApiProd(this.http)
  }
}
