import {Injectable} from '@angular/core';
import {ApiService} from "@services/api.service";

@Injectable({
  providedIn: 'root'
})
export class SatisfyApiService {
  private apiPoint: string = 'queue_tablet';

  constructor(private api: ApiService) {
  }

  setSatisfactionResult(result) {
    let endPoint = this.apiPoint + '/setSatisfactionResult';

    return this.api.get(endPoint, {"satisfactionResult":result})
      .toPromise()
      .then(res => res);
  }
}
