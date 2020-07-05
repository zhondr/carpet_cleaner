package kz.nu.carpet_cleaner.register.register.impl;

import kz.greetgo.util.RND;
import kz.nu.carpet_cleaner.controller.model.OrderRecord;
import kz.nu.carpet_cleaner.controller.register.OrderRegister;
import kz.nu.carpet_cleaner.register.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRegisterImpl implements OrderRegister {

  @Autowired
  private OrderDao orderDao;

  @Override
  public List<OrderRecord> getOrderList() {
    return orderDao.getList();
  }

  @Override
  public void deleteOrder(String orderId) {
    orderDao.deleteOrder(orderId);
  }

  @Override
  public OrderRecord upsertOrder(OrderRecord toSave) {
    if (toSave == null) return null;
    if (toSave.id == null) toSave.id = RND.strEng(30);
    orderDao.saveOrder(toSave);
    return getOrderDetail(toSave.id);
  }

  @Override
  public OrderRecord getOrderDetail(String orderId) {
    return orderDao.getOrderDetail(orderId);
  }
}
