package kz.nu.carpet_cleaner.controller.register;

import kz.nu.carpet_cleaner.controller.model.OrderRecord;

import java.util.List;

public interface OrderRegister {

  List<OrderRecord> getOrderList();

  void deleteOrder(String orderId);

  OrderRecord upsertOrder(OrderRecord toSave);

  OrderRecord getOrderDetail(String orderId);
}
