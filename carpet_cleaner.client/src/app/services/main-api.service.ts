import {Injectable} from '@angular/core';
import {ApiService} from "@services/api.service";
import {AuthorizationService} from "@services/authorization.service";
import {AuthUser} from "@models/authUser";

@Injectable({
  providedIn: 'root'
})
export class MainApiService {
  private apiPoint: string = 'queue_tablet';
  private user: AuthUser;

  constructor(private api: ApiService,
              private authorizationService: AuthorizationService) {

    this.authorizationService.currentUser.subscribe(x => this.user = x);
  }

  checkStatus() {
    let endPoint = this.apiPoint + '/checkStatus';

    return this.api.get(endPoint, {"employeeId": this.user.employeeId})
      .toPromise()
      .then(res => res);
  }

  saveResult(queueId:string, result:number) {
    let endPoint = this.apiPoint + '/result';

    return this.api.get(endPoint, {"employeeId": this.user.employeeId,
      "queueId": queueId,
      "result": result
    })
      .toPromise()
      .then(res => res);
  }
}
