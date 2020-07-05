package kz.nu.carpet_cleaner.register.dao;

import kz.nu.carpet_cleaner.controller.model.OrderRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {

  @Select("select * from cleaner_order")
  List<OrderRecord> getList();

  @Delete("delete from cleaner_order where id = #{orderId}")
  void deleteOrder(@Param("orderId") String orderId);

  @Insert("insert into cleaner_order(id, name, surname, phoneNumber, email) " +
      " values (#{toSave.id}, #{toSave.name}, #{toSave.surname}, #{toSave.phoneNumber}, #{toSave.email})" +
      " on conflict (id) do update " +
      " set id = #{toSave.id}, name = #{toSave.name}, surname = #{toSave.surname}, phoneNumber = #{toSave.phoneNumber}," +
      " email = #{toSave.email}")
  void saveOrder(@Param("toSave") OrderRecord toSave);

  @Select("select * from cleaner_order where id = #{orderId}")
  OrderRecord getOrderDetail(@Param("orderId") String orderId);
}
