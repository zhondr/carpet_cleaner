package kz.greetgo.aix_service_bus.register.test_dao;

import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdAccount;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdBroker;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInstrument;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInvestor;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdTrade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CsdTestDao {


  @Select("Select nextval(pg_get_serial_sequence('csd_trade', 'id')) as new_id")
  Long getTradeNextId();

  @Select("Select nextval(pg_get_serial_sequence('csd_investor', 'id')) as new_id")
  Long getInvestorNextId();

  @Select("Select nextval(pg_get_serial_sequence('csd_broker', 'id')) as new_id")
  Long getBrokerNextId();


  @Update("truncate csd_trade cascade")
  void deactualTrade();

  @Update("truncate csd_investor cascade")
  void deactualInvestor();

  @Update("truncate csd_broker cascade")
  void deactualBroker();

  @Update("truncate csd_instrument cascade")
  void deactualInstument();

  @Update("truncate csd_account cascade")
  void deactualAccount();

  @Insert("insert into csd_trade (csd_id, reference, market, trade_datetime, price_setting, instrument_code,"
      + "                       quantity, price, currency, sell_account, buy_account, settle_date, state,"
      + " sell_nin, sell_broker, buy_nin, buy_broker)"
      + " values (#{trade.csdId}, #{trade.reference}, #{trade.market}, "
      + "         #{trade.tradeDatetime}, #{trade.priceSetting}, #{trade.instrumentCode},"
      + "         #{trade.quantity}, #{trade.price}, #{trade.currency}, "
      + "         #{trade.sellAccount}, #{trade.buyAccount}, #{trade.settleDate}, #{trade.state},"
      + " #{trade.sellNin}, #{trade.sellBroker}, #{trade.buyNin}, #{trade.buyBroker})"
      + " on conflict ("
      + "   csd_id)"
      + "   do update set"
      + "     reference = EXCLUDED.reference,"
      + "     market = EXCLUDED.market,"
      + "     trade_datetime = EXCLUDED.trade_datetime,"
      + "     price_setting = EXCLUDED.price_setting,"
      + "     instrument_code = EXCLUDED.instrument_code,"
      + "     quantity = EXCLUDED.quantity,"
      + "     price = EXCLUDED.price,"
      + "     currency = EXCLUDED.currency,"
      + "     sell_account = EXCLUDED.sell_account,"
      + "     buy_account = EXCLUDED.buy_account,"
      + "     settle_date = EXCLUDED.settle_date,"
      + "     state = EXCLUDED.state,"
      + "     sell_nin = EXCLUDED.sell_nin,"
      + "     sell_broker = EXCLUDED.sell_broker,"
      + "     buy_nin = EXCLUDED.buy_nin,"
      + "     buy_broker = EXCLUDED.buy_broker,"
      + "     last_update = now();")
  void saveTrade(@Param("trade") CsdTrade trade);


  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into "
      + " csd_investor (name,short,preferred,address1,address2,address3,address4,country,email,phone,mobile)"
      + " values ("
      + "    #{in.name}, #{in.shortName}, #{in.preferred}, #{in.address1}, #{in.address2}, "
      + "    #{in.address3}, #{in.address4}, #{in.country}, #{in.email}, #{in.phone}, #{in.mobile}"
      + " )"
      + " on conflict (short) do UPDATE set"
      + "    name = EXCLUDED.name,"
      + "    preferred = EXCLUDED.preferred,"
      + "    address1 = EXCLUDED.address1,"
      + "    address2 = EXCLUDED.address2,"
      + "    address3 = EXCLUDED.address3,"
      + "    address4 = EXCLUDED.address4,"
      + "    country = EXCLUDED.country,"
      + "    email = EXCLUDED.email,"
      + "    phone = EXCLUDED.phone,"
      + "    mobile = EXCLUDED.mobile,"
      + "    last_update = now()"
  )
  void saveInvestor(@Param("in") CsdInvestor investor);

  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into csd_broker (name, short, type, state, country)"
      + " values (#{broker.name}, #{broker.shortName}, #{broker.type}, #{broker.state}, #{broker.country})"
      + " on conflict (short) do update set"
      + "     name = EXCLUDED.name,"
      + "     type = EXCLUDED.type,"
      + "     state = EXCLUDED.state,"
      + "     country = EXCLUDED.country,"
      + "     last_update = now()"
  )
  void saveBroker(@Param("broker") CsdBroker csdBroker);

  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into csd_instrument (name, short, ticker, fix, isin, mic, issuer, margin, delivery_margin,"
      + "                            haircut, yield, min_holding, min_transaction, notes, lot_size, accrued,"
      + "                            issued, collateral, go_live, maturity, settlement, face_value, close,"
      + "                            last, dsp, currency, underlying, state)"
      + " values (#{instrument.name}, #{instrument.shortName}, #{instrument.ticker}, #{instrument.fix},"
      + "        #{instrument.isin}, #{instrument.mic}, #{instrument.issuer}, #{instrument.margin},"
      + "        #{instrument.deliveryMargin}, #{instrument.haircut}, #{instrument.yield},"
      + "        #{instrument.minHolding}, #{instrument.minTransaction}, #{instrument.notes},"
      + "        #{instrument.lotSize}, #{instrument.accrued}, #{instrument.issued},"
      + "        #{instrument.collateral}, #{instrument.goLive}, #{instrument.maturity},"
      + "        #{instrument.settlement}, #{instrument.faceValue}, #{instrument.close},"
      + "        #{instrument.last}, #{instrument.dsp}, #{instrument.currency}, #{instrument.underlying},"
      + "        #{instrument.state})"
      + " on conflict (short) do update set"
      + "     name = EXCLUDED.name ,"
      + "     short = EXCLUDED.short ,"
      + "     ticker = EXCLUDED.ticker ,"
      + "     fix = EXCLUDED.fix ,"
      + "     isin = EXCLUDED.isin ,"
      + "     mic = EXCLUDED.mic ,"
      + "     issuer = EXCLUDED.issuer ,"
      + "     margin = EXCLUDED.margin ,"
      + "     delivery_margin = EXCLUDED.delivery_margin ,"
      + "     haircut = EXCLUDED.haircut ,"
      + "     yield = EXCLUDED.yield ,"
      + "     min_holding = EXCLUDED.min_holding ,"
      + "     min_transaction = EXCLUDED.min_transaction ,"
      + "     notes = EXCLUDED.notes ,"
      + "     lot_size = EXCLUDED.lot_size ,"
      + "     accrued = EXCLUDED.accrued ,"
      + "     issued = EXCLUDED.issued ,"
      + "     collateral = EXCLUDED.collateral ,"
      + "     go_live = EXCLUDED.go_live ,"
      + "     maturity = EXCLUDED.maturity ,"
      + "     settlement = EXCLUDED.settlement ,"
      + "     face_value = EXCLUDED.face_value ,"
      + "     close = EXCLUDED.close ,"
      + "     last = EXCLUDED.last ,"
      + "     dsp = EXCLUDED.dsp ,"
      + "     currency = EXCLUDED.currency ,"
      + "     underlying = EXCLUDED.underlying ,"
      + "     state = EXCLUDED.state,"
      + "     last_update = EXCLUDED.last_update")
  void saveInstrument(@Param("instrument") CsdInstrument instrument);

  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("insert into csd_account"
      + " (alias, short, name, location, currency, category, relation)"
      + " VALUES (#{account.alias}, #{account.shortName}, #{account.name}, #{account.location},"
      + "        #{account.currency}, #{account.category}, #{account.relation})"
      + " on conflict ("
      + "   alias)"
      + "   do update set"
      + "     short = EXCLUDED.short,"
      + "     name = EXCLUDED.name,"
      + "     location = EXCLUDED.location,"
      + "     currency = EXCLUDED.currency,"
      + "     category = EXCLUDED.category,"
      + "     relation = EXCLUDED.relation,"
      + "     last_update = now()"
  )
  void saveAccount(@Param("account") CsdAccount account);

}
