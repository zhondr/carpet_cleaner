package kz.greetgo.aix_service_bus.register.util;


import kz.greetgo.aix_service_bus.register.test_dao.CsdTestDao;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdBroker;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdInvestor;
import kz.greetgo.service_bus.controller.in_service.csd.model.response.CsdTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntitySaver {

  @Autowired
  private CsdTestDao csdTestDao;


  public void save(CsdInvestor investor) {
    csdTestDao.saveInvestor(investor);
  }

  public void save(CsdBroker csdBroker) {
    csdTestDao.saveBroker(csdBroker);
  }

  public void save(CsdTrade trade) {
    csdTestDao.saveTrade(trade);
  }

}
