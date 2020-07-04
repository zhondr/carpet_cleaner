import { Component } from '@angular/core';
import {NbDialogService} from "@nebular/theme";
import {OrderService} from "../../services/order.service";
import {OrderDialogComponent} from "./modal/order-dialog.component";
import {OrderRecord} from "./model/OrderRecord";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss'],
})
export class OrderComponent {

  settings = {
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      firstName: {
        title: 'First Name',
        type: 'string',
      },
      lastName: {
        title: 'Last Name',
        type: 'string',
      },
      username: {
        title: 'Username',
        type: 'string',
      },
      email: {
        title: 'E-mail',
        type: 'string',
      },
      age: {
        title: 'Age',
        type: 'number',
      },
    },
  };

  source: OrderRecord[];

  constructor(private dialogService:NbDialogService, private orderService:OrderService) {
  }

  loadData() {
    this.orderService
      .loadOrderList().then(res=> {
        this.source = res.orderList;
    });
  }

  add() {
    this.openDialog()
  }

  edit(id:string) {
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


}
