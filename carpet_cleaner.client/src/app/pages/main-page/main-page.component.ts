import { Component, OnInit } from '@angular/core';
import {Satisfy} from "@models/enums/satisfy";
import {timer} from 'rxjs';
import {SatisfyApiService} from "@services/satisfy-api.service";
import {MainApiService} from "@services/main-api.service";
import {ResultRecord} from "@models/resultRecord";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
  resultList: ResultRecord [];
  isWaiting: boolean = true;
  sayThanks: boolean = false;

  starResultList = [
    new ResultRecord({imgSrc: "assets/img/zvezda1.png", satisfyType: Satisfy.UNSATISFIED}),
    new ResultRecord({imgSrc: "assets/img/zvezda2.png", satisfyType: Satisfy.NEUTRAL}),
    new ResultRecord({imgSrc: "assets/img/zvezda3.png", satisfyType: Satisfy.SATISFIED}),
  ];

  smileResultList = [
    new ResultRecord({imgSrc: "assets/img/smile1.png", satisfyType: Satisfy.UNSATISFIED}),
    new ResultRecord({imgSrc: "assets/img/smile2.png", satisfyType: Satisfy.NEUTRAL}),
    new ResultRecord({imgSrc: "assets/img/smile3.png", satisfyType: Satisfy.SATISFIED}),
  ];

  buttonResultList = [
    new ResultRecord({satisfyType: Satisfy.UNSATISFIED,
      isButton:true, buttonText:"unsatisfied", elClass:"btn-red"}),
    new ResultRecord({satisfyType: Satisfy.NEUTRAL,
      isButton:true, buttonText:"neutral", elClass:"btn-yellow"}),
    new ResultRecord({satisfyType: Satisfy.SATISFIED,
      isButton:true, buttonText:"satisfied", elClass:"btn-green"}),
  ];

  constructor(private satisfyApiService:SatisfyApiService,
            private mainApiService:MainApiService) { }

  ngOnInit() {
    const timerSource = timer(500, 6 * 1000);
    timerSource.subscribe(() => {
      if(this.isWaiting || this.sayThanks) {
        this.mainApiService.checkStatus().then(statusRecord => {
          if (statusRecord.status === "WORKING") {
            this.isWaiting = false;
            this.sayThanks = false;
            this.drawIcon(statusRecord.iconType);
          } else {
            this.isWaiting = true;
          }
        });
      }
    })
  }

  drawIcon(iconType:string) {
    this.resultList = [];
    if(iconType==="SMILE") {
      this.resultList = this.smileResultList;
    } else if(iconType==="STAR") {
      this.resultList = this.starResultList;
    } else if(iconType==="BUTTON"){
      this.resultList = this.buttonResultList;
    }
  }
  satisfyAnswer(ans:string) {
    this.satisfyApiService.setSatisfactionResult(ans)
      .then(() => {
        this.sayThanks = true;
      });
  }
}
