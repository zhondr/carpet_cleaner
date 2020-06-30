import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {AuthUser} from "@models/authUser";
import {ApiService} from "@services/api.service";
import {Role} from "@models/enums/role";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  private apiPoint: string = 'queue_tablet';

  private currentUserSubject: BehaviorSubject<AuthUser>;
  currentUser: Observable<AuthUser>;

  constructor(private api: ApiService) {
    this.currentUserSubject = new BehaviorSubject<AuthUser>(JSON.parse(sessionStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  get currentUserValue(): AuthUser {
    return this.currentUserSubject.value;
  }

  authorize(iin) {
    let endPoint = this.apiPoint + '/authorize';

    return this.api.post(endPoint, {iin: iin}).toPromise()
      .then(user => {
        user.role = Role[user.role];

        this.verify(user);

        return user;
      });
  }

  logout(): Promise<any> {
    if (!this.currentUserValue) {
      return;
    }

    let endPoint = this.apiPoint + '/logout';
    let token = this.currentUserValue.token;

    this.unVerify();

    return this.api.get(endPoint, null, {'Authorization': token}).toPromise();
  }

  private verify(user: AuthUser) {
    sessionStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  private unVerify() {
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
