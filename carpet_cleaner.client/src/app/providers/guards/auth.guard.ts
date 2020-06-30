import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {AuthorizationService} from "@services/authorization.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authorizationService: AuthorizationService
  ) {

  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    let currentUser = this.authorizationService.currentUserValue;

    if (currentUser) {
      return true;
    }

    let tree: UrlTree = this.router.parseUrl('login');
    tree.queryParams.returnUrl = state.url;

    return tree;
  }
}
