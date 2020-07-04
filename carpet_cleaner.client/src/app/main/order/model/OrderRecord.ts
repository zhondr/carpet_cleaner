export class OrderRecord {
  name:string;
  surname:string;
  patronymic:string;
  phoneNumber:string;
  email:string;

  public constructor(init?: Partial<OrderRecord>) {
    Object.assign(this, init);
  }
}
