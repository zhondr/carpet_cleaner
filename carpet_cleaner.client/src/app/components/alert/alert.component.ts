import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {AlertService} from '@services/alert.service';
import {Alert} from '@models/alert';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit, OnDestroy {

  private subscription: Subscription;

  alert: Alert;
  cssClass: string;

  constructor(
    private alertService: AlertService
  ) {
  }

  ngOnInit() {
    this.subscription = this.alertService.getAlert()
      .subscribe(alert => {
        switch (alert && alert.type) {
          case 'success':
            this.cssClass = 'alert-success';
            break;
          case 'error':
            this.cssClass = 'alert-danger';
            break;
        }

        this.alert = alert;
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  close() {
    this.alertService.clear();
  }

}
