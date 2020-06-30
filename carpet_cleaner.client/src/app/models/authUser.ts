import {Employee} from "@models/employee";

export class AuthUser extends Employee {
  token: string;
}
