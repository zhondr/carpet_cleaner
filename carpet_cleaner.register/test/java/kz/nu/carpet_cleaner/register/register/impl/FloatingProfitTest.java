package kz.greetgo.aix_service_bus.register.register.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Date;
import kz.greetgo.aix_service_bus.register.TestConfiguration;
import kz.greetgo.aix_service_bus.register.test_dao.CsdTestDao;
import kz.greetgo.aix_service_bus.register.test_dao.CustomCsdTestDao;
import kz.greetgo.aix_service_bus.register.util.EntitySaver;
import kz.greetgo.aix_service_bus.register.util.RND;
import kz.greetgo.aix_service_bus.register.util.RandomEntity;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdBroker;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInvestor;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdTrade;
import kz.greetgo.service_bus.controller.in_service.marker_watch.MarketWatchInService;
import kz.greetgo.service_bus.controller.in_service.marker_watch.model.TradingSummary;
import kz.greetgo.service_bus.controller.in_service.mtgw.model.NbKztRate;
import kz.greetgo.service_bus.controller.model.FloatingProfit;
import kz.greetgo.service_bus.controller.register.CustomCsdRegister;
import org.assertj.core.data.Offset;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class FloatingProfitTest {

  @Autowired
  private EntitySaver entitySaver;

  @Autowired
  private CsdTestDao csdTradeTestDao;

  @Autowired
  private CustomCsdTestDao customCsdTradeTestDao;

  @SpyBean
  private CustomCsdRegister customCsdRegister;

  @Autowired
  private RandomEntity randomEntity;

  @Autowired
  private MarketWatchInService marketWatchInService;

  @Test
  public void fp_positive_only_buy_market_opened() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor csdInvestor = randomEntity.csdInvestor();
    entitySaver.save(csdInvestor);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
    csdTrade.quantity = 10L;
    csdTrade.price = 20L;
    entitySaver.save(csdTrade);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.closePrice = 25D;
    tradingSummary.highPrice = 20D;
    tradingSummary.lastTradeDate = new Date();
    tradingSummary.lastTradePrice = 21D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(csdTrade.instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(csdInvestor.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTrade.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTrade.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary.lastTradePrice);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(10);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(5);
    assertThat(floatingProfit.profit).isEqualTo(10);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(5);
  }

  @Test
  public void fp_positive_open_position() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor csdInvestor = randomEntity.csdInvestor();
    entitySaver.save(csdInvestor);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
    csdTrade.quantity = 10L;
    csdTrade.price = 20L;
    entitySaver.save(csdTrade);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.closePrice = 25D;
    tradingSummary.highPrice = 20D;
    tradingSummary.lastTradeDate = new Date();
    tradingSummary.lastTradePrice = 21D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(csdTrade.instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(csdInvestor.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTrade.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTrade.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary.lastTradePrice);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(10);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(5);
    assertThat(floatingProfit.profit).isEqualTo(10);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(5);
  }

  @Test
  public void fp_negative_only_buy_market_opened() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor csdInvestor = randomEntity.csdInvestor();
    entitySaver.save(csdInvestor);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
    csdTrade.quantity = 10L;
    csdTrade.price = 20L;
    entitySaver.save(csdTrade);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.closePrice = 25D;
    tradingSummary.highPrice = 20D;
    tradingSummary.lastTradeDate = new Date();
    tradingSummary.lastTradePrice = 19D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(csdTrade.instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(csdInvestor.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTrade.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTrade.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary.lastTradePrice);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(-10);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(-5);
    assertThat(floatingProfit.profit).isEqualTo(-10);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(-5);
  }


  @Test
  public void fp_positive_only_buy_market_closed() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor csdInvestor = randomEntity.csdInvestor();
    entitySaver.save(csdInvestor);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    String instrumentCode = RND.strInt(5);
    String currency = "USD";

    {
      CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
      csdTrade.tradeDatetime = RND.dateDays(-10, -2);
      csdTrade.quantity = 100L;
      csdTrade.price = 11L;
      csdTrade.instrumentCode = instrumentCode;
      csdTrade.currency = currency;
      entitySaver.save(csdTrade);
    }
    {
      CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
      csdTrade.tradeDatetime = RND.dateDays(-10, -2);
      csdTrade.buyNin = RND.str(5);
      csdTrade.buyBroker = RND.str(5);
      csdTrade.buyAccount = csdTrade.buyBroker + "-" + csdTrade.buyNin;
      csdTrade.quantity = 50L;
      csdTrade.price = 10L;
      csdTrade.instrumentCode = instrumentCode;
      csdTrade.currency = currency;
      entitySaver.save(csdTrade);
    }
    {
      CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
      csdTrade.tradeDatetime = RND.dateDays(-10, -2);
      csdTrade.quantity = 75L;
      csdTrade.price = 10L;
      csdTrade.instrumentCode = instrumentCode;
      csdTrade.currency = currency;
      entitySaver.save(csdTrade);
    }

    {
      CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
      csdTrade.tradeDatetime = RND.dateDays(-2, 0);
      csdTrade.quantity = 100L;
      csdTrade.price = 11L;
      csdTrade.instrumentCode = instrumentCode;
      csdTrade.currency = currency;
      entitySaver.save(csdTrade);
    }
    {
      CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
      csdTrade.tradeDatetime = RND.dateDays(-2, 0);
      csdTrade.buyNin = RND.str(5);
      csdTrade.buyBroker = RND.str(5);
      csdTrade.buyAccount = csdTrade.buyBroker + "-" + csdTrade.buyNin;
      csdTrade.quantity = 40L;
      csdTrade.price = 11L;
      csdTrade.instrumentCode = instrumentCode;
      csdTrade.currency = currency;
      entitySaver.save(csdTrade);
    }

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.previousClose = 12D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(csdInvestor.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary.previousClose);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(210);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(10.45, Offset.offset(0.1));
    assertThat(floatingProfit.profit).isEqualTo(210);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(10.45, Offset.offset(0.1));
  }


  @Test
  public void fp_without_trades() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor csdInvestor = randomEntity.csdInvestor();
    entitySaver.save(csdInvestor);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.previousClose = 21D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject("test");
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(csdInvestor.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(0);
    assertThat(floatingProfit.profit).isEqualTo(0);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(0);
  }

  @Test
  @Ignore
  public void fp_with_multiple_shares_trades() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor owner = randomEntity.csdInvestor();
    entitySaver.save(owner);

    CsdInvestor buyer = randomEntity.csdInvestor();
    entitySaver.save(buyer);

    CsdInvestor buyer2 = randomEntity.csdInvestor();
    entitySaver.save(buyer2);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTradeOne1 = randomEntity.csdTrade(owner, csdBroker);
    csdTradeOne1.instrumentCode = "AabbccDD";
    csdTradeOne1.quantity = 10L;
    csdTradeOne1.price = 10L;
    entitySaver.save(csdTradeOne1);

    CsdTrade csdTradeOne2 = randomEntity.csdTrade(owner, csdBroker);
    csdTradeOne2.instrumentCode = csdTradeOne1.instrumentCode;
    csdTradeOne2.quantity = 10L;
    csdTradeOne2.price = 15L;
    entitySaver.save(csdTradeOne2);

    CsdTrade csdTradeOne3 = randomEntity.csdTrade(owner, csdBroker);
    csdTradeOne3.instrumentCode = csdTradeOne1.instrumentCode;
    csdTradeOne3.quantity = 10L;
    csdTradeOne3.price = 20L;
    entitySaver.save(csdTradeOne3);

    CsdTrade csdTradeOne4 = randomEntity.csdTrade(owner, csdBroker, buyer);
    csdTradeOne4.instrumentCode = csdTradeOne1.instrumentCode;
    csdTradeOne4.quantity = 20L;
    csdTradeOne4.price = 27L;
    entitySaver.save(csdTradeOne4);

    CsdTrade csdTradeTwo1 = randomEntity.csdTrade(owner, csdBroker);
    csdTradeTwo1.instrumentCode = "BbAAcc";
    csdTradeTwo1.quantity = 5L;
    csdTradeTwo1.price = 5L;
    entitySaver.save(csdTradeTwo1);

    CsdTrade csdTradeThree = randomEntity.csdTrade(owner, csdBroker);
    csdTradeThree.instrumentCode = "Casdasd";
    csdTradeThree.quantity = 5L;
    csdTradeThree.price = 5L;
    entitySaver.save(csdTradeThree);

    CsdTrade csdTradeThree2 = randomEntity.csdTrade(owner, csdBroker, buyer2);
    csdTradeThree2.instrumentCode = csdTradeThree.instrumentCode;
    csdTradeThree2.quantity = 2L;
    csdTradeThree2.price = 1L;
    entitySaver.save(csdTradeThree2);

    TradingSummary tradingSummary1 = new TradingSummary();
    tradingSummary1.previousClose = 21D;

    Mockito.doReturn(tradingSummary1).when(marketWatchInService).getTradingSummaryAsObject(csdTradeOne1.instrumentCode);

    TradingSummary tradingSummary2 = new TradingSummary();
    tradingSummary2.lastTradePrice = 2D;

    Mockito.doReturn(tradingSummary2).when(marketWatchInService).getTradingSummaryAsObject(csdTradeTwo1.instrumentCode);

    TradingSummary tradingSummary3 = new TradingSummary();
    tradingSummary3.lastTradePrice = 1D;

    Mockito.doReturn(tradingSummary3).when(marketWatchInService)
        .getTradingSummaryAsObject(csdTradeThree.instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(owner.nin);
    //
    //3
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(3);

    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTradeOne1.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTradeOne1.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary1.previousClose);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(18);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(75);

    assertThat(floatingProfit.shares.get(1).currency).isEqualTo(csdTradeTwo1.currency);
    assertThat(floatingProfit.shares.get(1).secCode).isEqualTo(csdTradeTwo1.instrumentCode);
    assertThat(floatingProfit.shares.get(1).price).isEqualTo(tradingSummary2.previousClose);
    assertThat(floatingProfit.shares.get(1).priceChange).isEqualTo(18);
    assertThat(floatingProfit.shares.get(1).percentChange).isEqualTo(75);

    assertThat(floatingProfit.shares.get(2).currency).isEqualTo(csdTradeThree.currency);
    assertThat(floatingProfit.shares.get(2).secCode).isEqualTo(csdTradeThree.instrumentCode);
    assertThat(floatingProfit.shares.get(2).price).isEqualTo(tradingSummary3.previousClose);
    assertThat(floatingProfit.shares.get(2).priceChange).isEqualTo(18);
    assertThat(floatingProfit.shares.get(2).percentChange).isEqualTo(75);

    assertThat(floatingProfit.profit).isEqualTo(18);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(75);
  }

  @Test
  public void fp_positive_sell_and_buy_market_closed() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor owner = randomEntity.csdInvestor();
    entitySaver.save(owner);

    CsdInvestor buyer = randomEntity.csdInvestor();
    entitySaver.save(buyer);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTrade1 = randomEntity.csdTrade(owner, csdBroker);
    csdTrade1.quantity = 5L;
    csdTrade1.price = 20L;
    entitySaver.save(csdTrade1);

    CsdTrade csdTrade2 = randomEntity.csdTrade(owner, csdBroker);
    csdTrade2.instrumentCode = csdTrade1.instrumentCode;
    csdTrade2.quantity = 7L;
    csdTrade2.price = 15L;
    entitySaver.save(csdTrade2);

    CsdTrade csdTrade3 = randomEntity.csdTrade(owner, csdBroker);
    csdTrade3.instrumentCode = csdTrade1.instrumentCode;
    csdTrade3.quantity = 10L;
    csdTrade3.price = 12L;
    entitySaver.save(csdTrade3);

    CsdTrade csdTrade4 = randomEntity.csdTrade(owner, csdBroker, buyer);
    csdTrade4.instrumentCode = csdTrade1.instrumentCode;
    csdTrade4.quantity = 20L;
    csdTrade4.price = 11L;
    entitySaver.save(csdTrade4);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.previousClose = 21D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(csdTrade1.instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(owner.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTrade1.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTrade1.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary.previousClose);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(-63);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(-60, Offset.offset(0.9));
    assertThat(floatingProfit.profit).isEqualTo(-63);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(-60, Offset.offset(0.9));
  }

  @Test
  public void fp_positive_buy_market_closed() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();

    CsdInvestor owner = randomEntity.csdInvestor();
    entitySaver.save(owner);

    CsdInvestor buyer = randomEntity.csdInvestor();
    entitySaver.save(buyer);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTrade1 = randomEntity.csdTrade(owner, csdBroker);
    csdTrade1.quantity = 1L;
    csdTrade1.price = 5000L;
    entitySaver.save(csdTrade1);

    CsdTrade csdTrade2 = randomEntity.csdTrade(owner, csdBroker);
    csdTrade2.instrumentCode = csdTrade1.instrumentCode;
    csdTrade2.quantity = 1L;
    csdTrade2.price = 5100L;
    entitySaver.save(csdTrade2);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.previousClose = 5500D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(csdTrade1.instrumentCode);
    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(owner.nin);
    //
    //
    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTrade1.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTrade1.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price).isEqualTo(tradingSummary.previousClose);
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(900);
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(8.92, Offset.offset(0.9));
    assertThat(floatingProfit.profit).isEqualTo(900);
    assertThat(floatingProfit.profitPercentChange).isEqualTo(8.92, Offset.offset(0.9));
  }

  @Test
  public void fp_currency_calculation() {
    csdTradeTestDao.deactualBroker();
    csdTradeTestDao.deactualTrade();
    csdTradeTestDao.deactualInvestor();
    customCsdTradeTestDao.deleteRates();

    NbKztRate from = randomEntity.kztRate();
    customCsdTradeTestDao.saveRate(from);

    NbKztRate to = randomEntity.kztRate();
    customCsdTradeTestDao.saveRate(to);

    CsdInvestor csdInvestor = randomEntity.csdInvestor();
    entitySaver.save(csdInvestor);

    CsdBroker csdBroker = randomEntity.csdBroker();
    entitySaver.save(csdBroker);

    CsdTrade csdTrade = randomEntity.csdTrade(csdInvestor, csdBroker);
    csdTrade.quantity = 10L;
    csdTrade.price = 20L;
    csdTrade.currency = from.currency;
    entitySaver.save(csdTrade);

    TradingSummary tradingSummary = new TradingSummary();
    tradingSummary.closePrice = 25D;
    tradingSummary.highPrice = 20D;
    tradingSummary.lastTradeDate = new Date();
    tradingSummary.lastTradePrice = 21D;

    Mockito.doReturn(tradingSummary).when(marketWatchInService).getTradingSummaryAsObject(csdTrade.instrumentCode);

    double coefficient = from.rate / to.rate;

    //
    //
    FloatingProfit floatingProfit = customCsdRegister.getFloatingProfit(csdInvestor.nin, to.currency);
    //
    //

    assertThat(floatingProfit).isNotNull();
    assertThat(floatingProfit.shares).isNotNull();
    assertThat(floatingProfit.shares.size()).isEqualTo(1);
    assertThat(floatingProfit.shares.get(0).currency).isEqualTo(csdTrade.currency);
    assertThat(floatingProfit.shares.get(0).secCode).isEqualTo(csdTrade.instrumentCode);
    assertThat(floatingProfit.shares.get(0).price)
        .isEqualTo(tradingSummary.lastTradePrice * coefficient, Offset.offset(0.01));
    assertThat(floatingProfit.shares.get(0).priceChange).isEqualTo(10 * coefficient, Offset.offset(0.01));
    assertThat(floatingProfit.shares.get(0).percentChange).isEqualTo(5);
    assertThat(floatingProfit.profit).isEqualTo(10 * coefficient, Offset.offset(0.01));
    assertThat(floatingProfit.profitPercentChange).isEqualTo(5);
  }

}

