import {KMFUser} from "@models/KMFUsers/KMFUser";
import {AuthUser} from "@models/authUser";
import {Role} from "@models/enums/role";
import {KMFObserver} from "@models/KMFUsers/KMFObserver";
import {KMFGenManager} from "@models/KMFUsers/KMFGenManager";
import {KMFManager} from "@models/KMFUsers/KMFManager";
import {KMFTestUser} from "@models/KMFUsers/KMFTestUser";

export class UserDefinition {

  static defineUser(authUser: AuthUser): KMFUser {
    let user: Partial<KMFUser> = Object.assign(new KMFUser(), authUser);

    switch (authUser.role) {
      case Role.MANAGER:
        return Object.assign(user, new KMFManager());

      case Role.GENERAL_MANAGER:
        return Object.assign(user, new KMFGenManager());

      case Role.MONITORING_SPECIALIST:
        return Object.assign(user, new KMFObserver());

      case Role.TEST:
        return Object.assign(user, new KMFTestUser());

      default:
        return Object.assign(user, new KMFUser());
    }
  }
}
