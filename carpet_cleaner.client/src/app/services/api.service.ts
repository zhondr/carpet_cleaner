import {Injectable} from '@angular/core';
import {IApi} from '@interfaces/iapi';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private _api: IApi;

  set api(api: IApi) {
    this._api = api;
  }

  constructor() {

  }

  get(endpoint: string, params?: any, reqOpts?: any): Observable<any> {
    return this._api.get(endpoint, params, reqOpts);
  }

  post(endpoint: string, body: any, reqOpts?: any): Observable<any> {
    return this._api.post(endpoint, body, reqOpts);
  }
}
