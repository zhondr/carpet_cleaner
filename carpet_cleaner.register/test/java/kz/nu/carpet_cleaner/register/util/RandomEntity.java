package kz.greetgo.aix_service_bus.register.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.function.Function;
import kz.greetgo.aix_service_bus.register.test_dao.CsdTestDao;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdAccount;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdAccountBalance;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdBroker;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInstrument;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInvestor;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdPageResponse;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdPaging;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdTrade;
import kz.greetgo.service_bus.controller.in_service.mtgw.model.NbKztRate;
import kz.greetgo.service_bus.controller.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomEntity {

  @Autowired
  private CsdTestDao csdTestDao;


  public CsdBroker csdBroker() {
    CsdBroker csdBroker = new CsdBroker();
    csdBroker.id = csdTestDao.getBrokerNextId();
    csdBroker.name = RND.strEng(10);
    csdBroker.shortName = RND.strEng(5);
    csdBroker.type = RND.plusLong(10000);
    csdBroker.state = RND.strEng(10);
    csdBroker.country = RND.rndCounty();
    return csdBroker;
  }

  public CsdInvestor csdInvestor() {
    CsdInvestor investor = new CsdInvestor();
    investor.id = csdTestDao.getInvestorNextId();
    investor.nin = RND.strEng(10);
    investor.name = RND.strEng(10);
    investor.shortName = RND.strEng(5);
    investor.preferred = RND.strEng(5);
    investor.address1 = RND.strEng(10);
    investor.address2 = RND.strEng(10);
    investor.address3 = RND.strEng(10);
    investor.address4 = RND.strEng(10);
    investor.country = RND.rndCounty();
    investor.email = RND.rndEmail(10);
    investor.phone = RND.rndPhone(11);
    investor.mobile = RND.rndPhone(11);
    return investor;
  }


  public CsdTrade csdTrade(CsdInvestor investor, CsdBroker broker) {
    CsdTrade csdTrade = new CsdTrade();
    csdTrade.id = csdTestDao.getTradeNextId();
    csdTrade.csdId = "CSD_ID" + csdTrade.id;
    csdTrade.reference = RND.strEng(8);
    csdTrade.market = "AIX";
    csdTrade.priceSetting = RND.bool();
    csdTrade.tradeDatetime = new Date(new Date().getTime() - RND.plusInt(1000));
    csdTrade.instrumentCode = RND.strEng(3);
    csdTrade.quantity = (long) RND.plusInt(100);
    csdTrade.price = RND.plusLong(5000);
    csdTrade.currency = "USD";
    csdTrade.sellAccount = broker.shortName + "-" + investor.nin;
    csdTrade.buyAccount = broker.shortName + "-" + investor.nin;
    csdTrade.settleDate = new Date();
    csdTrade.state = RND.someString("Settled", "Failed");
    csdTrade.buyNin = investor.nin;
    csdTrade.buyBroker = broker.shortName;
    csdTrade.sellNin = investor.nin;
    csdTrade.sellBroker = broker.shortName;
    return csdTrade;
  }

  public CsdTrade csdTrade(CsdInvestor investor, CsdBroker broker, CsdInvestor buyer) {
    CsdTrade csdTrade = csdTrade(buyer, broker);
    if (buyer != null) {
      csdTrade.sellNin = investor.nin;
      csdTrade.sellBroker = broker.shortName;
    }
    return csdTrade;
  }

  public NbKztRate kztRate() {
    NbKztRate result = new NbKztRate();

    result.currency = RND.strEng(8);
    result.rate = RND.plusDouble(1000, 3);
    result.busTime = new Timestamp(new Date().getTime());
    result.lastUpdate = new Timestamp(new Date().getTime());

    return result;
  }

  public CsdInstrument csdInstrument() {
    CsdInstrument result = new CsdInstrument();

    result.name = RND.str(10);
    result.shortName = RND.str(10);
    result.ticker = RND.str(10);
    result.fix = RND.str(10);
    result.isin = RND.str(10);
    result.mic = RND.str(10);
    result.issuer = RND.str(10);
    result.margin = RND.plusDouble(10, 2);
    result.deliveryMargin = RND.plusDouble(10, 2);
    result.haircut = RND.plusDouble(10, 2);
    result.yield = RND.plusDouble(10, 2);
    result.minHolding = RND.plusDouble(10, 2);
    result.minTransaction = RND.plusDouble(10, 2);
    result.notes = RND.str(10);
    result.lotSize = RND.plusLong(10);
    result.accrued = RND.plusDouble(10, 2);
    result.issued = RND.plusLong(10);
    result.collateral = RND.bool();
    result.goLive = RND.dateDays(10, 20);
    result.maturity = RND.dateDays(10, 20);
    result.settlement = RND.dateDays(10, 20);
    result.faceValue = RND.plusDouble(10, 2);
    result.close = RND.plusDouble(10, 2);
    result.last = RND.plusDouble(10, 2);
    result.dsp = RND.plusDouble(10, 2);
    result.currency = RND.str(10);
    result.underlying = RND.str(10);
    result.state = RND.str(10);

    return result;
  }

  public CsdAccountBalance csdAccountBalance() {
    CsdAccountBalance balance = new CsdAccountBalance();
    balance.value = RND.plusDouble(40000, 2);
    balance.total = RND.plusLong(1000);
    balance.free = RND.plusLong(1000);
    balance.locked = RND.plusLong(1000);
    balance.reserved = RND.plusLong(1000);
    balance.instrumentCode = RND.strEng(10);
    return balance;
  }

  public <T> Response<CsdPageResponse<T>> createResponsePage(Function<Integer, T> entityCreator, int pageSize) {
    Response<CsdPageResponse<T>> csdPageResponseResponse = new Response<>();
    csdPageResponseResponse.isOk = true;
    csdPageResponseResponse.status = 200;
    csdPageResponseResponse.url = "";

    CsdPageResponse<T> accountBalancePage = new CsdPageResponse<>();
    CsdPaging paging = new CsdPaging();
    paging.pageCount = 1;
    paging.pageSize = pageSize;
    paging.total = pageSize;
    paging.page = 0;
    accountBalancePage.paging = paging;

    for (int i = 0; i < pageSize; i++) {
      accountBalancePage.data.add(entityCreator.apply(i));
    }

    csdPageResponseResponse.body = accountBalancePage;
    return csdPageResponseResponse;
  }

  public CsdAccount csdAccount() {
    CsdAccount result = new CsdAccount();
    result.alias = RND.str(10);
    result.shortName = RND.str(10);
    result.name = RND.str(10);
    result.location = RND.str(10);
    result.currency = RND.str(10);
    result.category = RND.str(10);
    result.relation = RND.str(10);
    return result;
  }

}
