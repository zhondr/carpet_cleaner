package kz.greetgo.aix_service_bus.register.test_dao;

import kz.greetgo.service_bus.controller.in_service.mtgw.model.NbKztRate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCsdTestDao {


  @Update("truncate kzt_rate cascade")
  void deleteRates();


  @Insert("insert into kzt_rate "
      + " (currency, rate,  busTime)"
      + " values"
      + " (upper(#{rate.currency}), #{rate.rate},  #{rate.busTime})"
      + " on conflict (currency) do update set "
      + " rate=EXCLUDED.rate,"
      + " busTime=EXCLUDED.busTime,"
      + " lastUpdate=now()")
  void saveRate(@Param("rate") NbKztRate rate);
}
