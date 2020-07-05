package kz.nu.carpet_cleaner.controller.controller;

import kz.nu.carpet_cleaner.controller.model.OrderRecord;
import kz.nu.carpet_cleaner.controller.register.OrderRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

  @Autowired
  private OrderRegister register;

  @GetMapping("list")
  public List<OrderRecord> orderList() {
    return register.getOrderList();
  }

  @PostMapping("detail")
  public OrderRecord orderDetail(@RequestBody String id) {
    return register.getOrderDetail(id);
  }

  @PostMapping("save")
  public OrderRecord orderUpdate(@RequestBody OrderRecord toSave) {
    return register.upsertOrder(toSave);
  }

  @PostMapping("delete")
  public void orderDelete(@RequestBody String id) {
    register.deleteOrder(id);
    return;
  }
}
