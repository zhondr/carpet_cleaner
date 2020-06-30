import {Role} from "@models/enums/role";

export class TestData {
  data = {
    queue_tablet: {
      authorize: {
        post(body: any) {
          return TestData.postAuthorize(body);
        }
      },
      setSatisfactionResult: {
        get(body: any) {
          return TestData.postSatisfactionResult(body);
        }
      },
      checkStatus: {
        get(body: any) {
          return TestData.checkStatus(body);
        }
      },
      logout: {
        get(params: any) {
          return true;
        }
      },
    }
  };

  private static checkStatus(body: any) {
    let userStatus: any = {};
    let number = Math.random();
    userStatus.status = number>=0.5?"WAITING":"WORKING";

    number = Math.random();
    userStatus.iconType = number<=0.5?"SMILE":"STAR";
    // userStatus.iconType = number<=0.6?"SMILE":userStatus.iconType;
    return userStatus;
  }

  private static postSatisfactionResult(body: any) {
    return {};
  }

  private static postAuthorize(body: any) {
    if (!body.iin) {
      throw Error('не все данные дошли!');
    }

    let user: any = {};

    user.role = Role[Role.TEST];
    user.token = 'jwt-token';
    user.name = 'Ник';
    user.surname = 'Админ';

    return user;
  }
}
