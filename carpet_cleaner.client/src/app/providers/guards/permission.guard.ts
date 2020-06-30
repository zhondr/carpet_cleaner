import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthorizationService} from "@services/authorization.service";
import {UserDefinition} from "@utils/user-definition";

@Injectable({
  providedIn: 'root'
})
export class PermissionGuard implements CanActivate {

  constructor(
    private router: Router,
    private authorizationService: AuthorizationService
  ) {
  }


  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let currentUser = this.authorizationService.currentUserValue;

    if (!currentUser) {
      this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
      return false;
    }

    let checkUrl = next.url.pop().path;
    let defUser = UserDefinition.defineUser(currentUser);

    let isHasAccess: boolean = defUser.hasAccess(checkUrl);

    if (!isHasAccess) {
      let firstUrl = defUser.urlList.values().next().value;

      if (firstUrl) {
        this.router.navigate(['tabs/' + firstUrl]);
      } else {
        this.authorizationService.logout()
          .then(() => {
            this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
          });
      }
    }

    return isHasAccess;
  }

}
