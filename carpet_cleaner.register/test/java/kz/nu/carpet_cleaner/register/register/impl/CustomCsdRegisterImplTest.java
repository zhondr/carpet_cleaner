package kz.greetgo.aix_service_bus.register.register.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

import java.util.List;
import kz.greetgo.aix_service_bus.register.TestConfiguration;
import kz.greetgo.aix_service_bus.register.test_dao.CsdTestDao;
import kz.greetgo.aix_service_bus.register.test_dao.CustomCsdTestDao;
import kz.greetgo.aix_service_bus.register.util.RandomEntity;
import kz.greetgo.service_bus.controller.in_service.aix.model.TradesFilter;
import kz.greetgo.service_bus.controller.in_service.csd.CsdInService;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdAccount;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdAccountBalance;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdBroker;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInstrument;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInvestor;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdPageResponse;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdTrade;
import kz.greetgo.service_bus.controller.in_service.mtgw.model.NbKztRate;
import kz.greetgo.service_bus.controller.model.Response;
import kz.greetgo.service_bus.controller.model.TradeRecord;
import kz.greetgo.service_bus.controller.register.CustomCsdRegister;
import kz.greetgo.util.RND;
import lombok.var;
import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class CustomCsdRegisterImplTest {

  @Autowired
  private CsdTestDao csdTestDao;

  @Autowired
  private CustomCsdTestDao customCsdTestDao;

  @SpyBean
  private CustomCsdRegister customCsdRegister;

  @Autowired
  private RandomEntity randomEntity;

  @Autowired
  private CsdInService csdInService;

  @Test
  @Repeat(value = 5)
  public void testGetTotalBalance() {
    csdTestDao.deactualInstument();
    customCsdTestDao.deleteRates();

    NbKztRate from = randomEntity.kztRate();
    customCsdTestDao.saveRate(from);

    NbKztRate to = randomEntity.kztRate();
    customCsdTestDao.saveRate(to);

    CsdInstrument csdInstrument = randomEntity.csdInstrument();
    csdInstrument.currency = from.currency;
    csdTestDao.saveInstrument(csdInstrument);

    String nin = RND.str(10);

    Response<CsdPageResponse<CsdAccountBalance>> response  = randomEntity.createResponsePage(integer -> {
      CsdAccountBalance balance = randomEntity.csdAccountBalance();
      balance.instrumentCode = csdInstrument.shortName;
      return balance;
    }, 10);

    double rateCoefficient = from.rate / to.rate;
    double sum = response.body.data.stream().mapToDouble(value -> value.value * rateCoefficient).sum();

    Mockito.doReturn(response).when(csdInService).getAccountBalanceAsObject(nin, null, null, 0, null);

    ///
    ///
    Double totalBalance = customCsdRegister.getTotalBalance(nin, to.currency);
    ///
    ///

    assertThat(totalBalance).isEqualTo(sum, Offset.offset(0.00001));

  }

  @Test
  public void testGetTotalBalance_default_currency() {
    csdTestDao.deactualInstument();
    customCsdTestDao.deleteRates();

    NbKztRate from = randomEntity.kztRate();
    customCsdTestDao.saveRate(from);

    NbKztRate to = randomEntity.kztRate();
    to.currency = "USD";
    customCsdTestDao.saveRate(to);

    CsdInstrument csdInstrument = randomEntity.csdInstrument();
    csdInstrument.currency = from.currency;
    csdTestDao.saveInstrument(csdInstrument);

    String nin = RND.str(10);

    Response<CsdPageResponse<CsdAccountBalance>> response = randomEntity.createResponsePage(integer -> {
      CsdAccountBalance balance = randomEntity.csdAccountBalance();
      balance.instrumentCode = csdInstrument.shortName;
      return balance;
    }, 10);

    double rateCoefficient = from.rate / to.rate;
    double sum = response.body.data.stream().mapToDouble(value -> value.value * rateCoefficient).sum();

    Mockito.doReturn(response).when(csdInService).getAccountBalanceAsObject(nin, null, null, 0, null);

    ///
    ///
    Double totalBalance = customCsdRegister.getTotalBalance(nin, null);
    ///
    ///

    assertThat(totalBalance).isEqualTo(sum, Offset.offset(0.00001));

  }

  @Test
  public void getTradesFromDB() {
    csdTestDao.deactualInvestor();
    csdTestDao.deactualBroker();
    csdTestDao.deactualTrade();
    csdTestDao.deactualAccount();
    customCsdTestDao.deleteRates();

    CsdInvestor investor = randomEntity.csdInvestor();
    csdTestDao.saveInvestor(investor);

    CsdBroker broker = randomEntity.csdBroker();
    csdTestDao.saveBroker(broker);

    NbKztRate from = randomEntity.kztRate();
    customCsdTestDao.saveRate(from);
    NbKztRate to = randomEntity.kztRate();
    customCsdTestDao.saveRate(to);

    CsdTrade csdTrade = randomEntity.csdTrade(investor, broker);
    csdTrade.currency = from.currency;
    csdTestDao.saveTrade(csdTrade);

    CsdAccount account = randomEntity.csdAccount();
    account.alias = csdTrade.buyAccount;
    account.currency = from.currency;
    account.relation = broker.shortName;
    account.shortName = investor.shortName;
    csdTestDao.saveAccount(account);

    TradesFilter filter = new TradesFilter();
    filter.shortCode = investor.shortName;
    filter.role = "INVESTOR";
    filter.displayCurrency = to.currency;

    ///
    ///

    List<TradeRecord> tradesFromDB = customCsdRegister.getTradesFromDB(filter);

    ///
    ///

    assertThat(tradesFromDB).isNotNull();
    assertThat(tradesFromDB).hasSize(1);

    assertThat(csdTrade.price.doubleValue()).isEqualTo(tradesFromDB.get(0).price, Offset.offset(1.0));

  }

  @Test
  public void getTradesFromDB_default_currency() {
    csdTestDao.deactualInvestor();
    csdTestDao.deactualBroker();
    csdTestDao.deactualTrade();
    csdTestDao.deactualAccount();
    customCsdTestDao.deleteRates();

    CsdInvestor investor = randomEntity.csdInvestor();
    csdTestDao.saveInvestor(investor);

    CsdBroker broker = randomEntity.csdBroker();
    csdTestDao.saveBroker(broker);

    NbKztRate from = randomEntity.kztRate();
    customCsdTestDao.saveRate(from);
    NbKztRate to = randomEntity.kztRate();
    to.currency = "USD";
    customCsdTestDao.saveRate(to);

    CsdTrade csdTrade = randomEntity.csdTrade(investor, broker);
    csdTrade.currency = from.currency;
    csdTestDao.saveTrade(csdTrade);

    CsdAccount account = randomEntity.csdAccount();
    account.alias = csdTrade.buyAccount;
    account.currency = from.currency;
    account.relation = broker.shortName;
    account.shortName = investor.shortName;
    csdTestDao.saveAccount(account);

    TradesFilter filter = new TradesFilter();
    filter.shortCode = investor.shortName;
    filter.role = "INVESTOR";

    ///
    ///

    List<TradeRecord> tradesFromDB = customCsdRegister.getTradesFromDB(filter);

    ///
    ///

    assertThat(tradesFromDB).isNotNull();
    assertThat(tradesFromDB).hasSize(1);

    assertThat(csdTrade.price.doubleValue()).isEqualTo(tradesFromDB.get(0).price, Offset.offset(0.99));

  }

}
