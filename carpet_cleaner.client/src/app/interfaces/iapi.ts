import {Observable} from 'rxjs';

export interface IApi {
  get(endpoint: string, params?: any, reqOpts?: any): Observable<any>

  post(endpoint: string, body: any, reqOpts?: any): Observable<any>
}
