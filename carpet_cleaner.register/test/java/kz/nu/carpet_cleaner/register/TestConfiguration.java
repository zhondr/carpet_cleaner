package kz.nu.carpet_cleaner.register;//package kz.greetgo.aix_service_bus.register;

import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import kz.nu.carpet_cleaner.register.configurations.beans.all.AppBeanScanner;
import org.mockito.Mockito;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = {"kz.nu.carpet_cleaner.register.test_dao"})
@ComponentScan(basePackages = {"kz.nu.carpet_cleaner.register.test_dao", "kz.nu.carpet_cleaner.register.util"})
@Import({AppBeanScanner.class, JdbcTemplateAutoConfiguration.class})
public class TestConfiguration {

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

