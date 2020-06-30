import {Role} from "@models/enums/role";

export class Employee {
  employeeId: string;
  role: Role;
  surname: string;
  name: string;

  public constructor(init?: Partial<Employee>) {
    Object.assign(this, init);
  }
}
