package kz.greetgo.aix_service_bus.register;//package kz.greetgo.aix_service_bus.register;

import kz.greetgo.aix_service_bus.register.configurations.beans.all.AppBeanScanner;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import kz.greetgo.service_bus.controller.in_service.acs.AcsInService;
import kz.greetgo.service_bus.controller.in_service.aix.AixInService;
import kz.greetgo.service_bus.controller.in_service.csd.CsdInService;
import kz.greetgo.service_bus.controller.in_service.indices.IndexInService;
import kz.greetgo.service_bus.controller.in_service.marker_watch.MarketWatchInService;
import kz.greetgo.service_bus.controller.in_service.mtgw.MtgwInService;
import kz.greetgo.service_bus.controller.in_service.qr.QrInService;
import kz.greetgo.service_bus.controller.in_service.twilio.TwilioInService;
import kz.greetgo.service_bus.controller.in_service.web_admin.WebAdminInService;
import org.mockito.Mockito;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = {"kz.greetgo.aix_service_bus.register.test_dao"})
@ComponentScan(basePackages = {"kz.greetgo.aix_service_bus.register.test_dao", "kz.greetgo.aix_service_bus.register.util"})
@Import({AppBeanScanner.class, JdbcTemplateAutoConfiguration.class})
public class TestConfiguration {

  @Bean
  @Primary
  public AcsInService acsInService() {
    return Mockito.mock(AcsInService.class);
  }

  @Bean
  @Primary
  public AixInService aixInService() {
    return Mockito.mock(AixInService.class);
  }

  @Bean
  @Primary
  public IndexInService indexInService() {
    return Mockito.mock(IndexInService.class);
  }

  @Bean
  @Primary
  public MarketWatchInService markerWatchInService() {
    return Mockito.mock(MarketWatchInService.class);
  }

  @Bean
  @Primary
  public MtgwInService mtgwInService() {
    return Mockito.mock(MtgwInService.class);
  }

  @Bean
  @Primary
  public QrInService qrInService() {
    return Mockito.mock(QrInService.class);
  }

  @Bean
  @Primary
  public TwilioInService twilioInService() {
    return Mockito.mock(TwilioInService.class);
  }

  @Bean
  @Primary
  public WebAdminInService webAdminInService() {
    return Mockito.mock(WebAdminInService.class);
  }

  @Bean
  @Primary
  public CsdInService csdInService() {
    return Mockito.mock(CsdInService.class);
  }

  @Bean
  @Primary
  public EmailSender emailSender() {
    return Mockito.mock(EmailSender.class);
  }


  @Bean
  @Primary
  public EmailSenderController emailSenderController() {
    return Mockito.mock(EmailSenderController.class);
  }


}

