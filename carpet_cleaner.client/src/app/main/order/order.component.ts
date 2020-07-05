import {Component, OnInit} from '@angular/core';
import {NbDialogService} from "@nebular/theme";
import {OrderService} from "../../services/order.service";
import {OrderDialogComponent} from "./modal/order-dialog.component";
import {OrderRecord} from "./model/OrderRecord";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss'],
})
export class OrderComponent implements OnInit {

  source: OrderRecord[];

  constructor(private dialogService:NbDialogService, private orderService:OrderService) {
  }

  loadData() {
    this.orderService
      .loadOrderList().then(res=> {
        console.log(res);
        this.source = res;
    });
  }

  add() {
    this.openDialog()
  }

  edit(id:string) {
    console.log(id);
    this.openDialog(id);
  }

  openDialog(id:string = null) {
    this.dialogService.open(OrderDialogComponent, {
      context: {
        id: id
      },
      hasBackdrop: true,
      closeOnEsc: true
    }).onClose.subscribe(res=>this.loadData());
  }

  delete(id:string) {
    this.orderService
      .deleteOrder(id)
      .then(res=>this.loadData())
  }

  ngOnInit(): void {
    this.loadData();
  }

}
