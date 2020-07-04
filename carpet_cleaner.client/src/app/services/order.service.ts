import {Injectable} from "@angular/core";
import {ApiService} from "./api.service";

@Injectable({
    providedIn: "root"
})
export class OrderService {

  private apiPoint: string = 'order';
  constructor(private api:ApiService) {

  }

  loadOrderList() {
    let endPoint = this.apiPoint + "/list";
    return this.api.get(endPoint, {}).toPromise()
      .then( res=>res);
  }

  deleteOrder(id: string) {
    let endPoint = this.apiPoint + "/delete";
    return this.api.post(endPoint, {id:id}).toPromise()
      .then( res=>res);
  }

  orderDetail(id: string) {
    let endPoint = this.apiPoint + "/detail";
    return this.api.post(endPoint, {id:id}).toPromise()
      .then( res=>res);
  }

  saveOrder(data: any) {
    let endPoint = this.apiPoint + "/detail";
    return this.api.post(endPoint, data).toPromise()
      .then( res=>res);
  }
}
