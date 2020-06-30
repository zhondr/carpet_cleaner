export class ResultRecord {
  imgSrc: string;
  satisfyType: string;
  isButton: boolean = false;
  buttonText: string;
  elClass: string;

  public constructor(init?: Partial<ResultRecord>) {
    Object.assign(this, init);
  }
}
