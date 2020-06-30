import {IApi} from '@interfaces/iapi';
import {Observable} from 'rxjs';
import {TestData} from "@models/test-data";

export class ApiTest implements IApi {
  data: any;

  constructor() {
    this.data = new TestData().data;
  }

  get(endpoint: string, params?: any, reqOpts?: any): Observable<object> {
    return new Observable(subscriber => {
      let path = endpoint.split('/');
      let data: any = this.data;

      for (let i = 0; i < path.length; i++) {
        data = data[path[i]];
      }

      if (!params)
        params = {};

      if (reqOpts && reqOpts["Accept-Language"])
        params["locale"] = reqOpts["Accept-Language"];

      subscriber.next(data.get(params));
      subscriber.complete();
    });
  }

  post(endpoint: string, body: any, reqOpts?: any): Observable<object> {
    return new Observable(subscriber => {
      let path = endpoint.split('/');
      let data: any = this.data;

      for (let i = 0; i < path.length; i++) {
        data = data[path[i]];
      }

      if (!body)
        body = {};

      if (reqOpts && reqOpts["Accept-Language"])
        body["locale"] = reqOpts["Accept-Language"];

      subscriber.next(data.post(body));
      subscriber.complete();
    });
  }

}
