package kz.nu.carpet_cleaner.register.configurations.beans.all;


import kz.nu.carpet_cleaner.register.hotconfig.DbConfig;
import kz.greetgo.conf.hot.FileConfigFactory;
import kz.nu.carpet_cleaner.controller.AppFolderPath;
import kz.nu.carpet_cleaner.register.hotconfig.EmailSenderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllConfigFactory extends FileConfigFactory {


  @Override
  protected String getBaseDir() {
    return AppFolderPath.confDif();
  }

  @Bean
  public DbConfig createPostgresDbConfig() {
    return createConfig(DbConfig.class);
  }


  @Bean
  public EmailSenderConfig createEmailSenderConfig() {
    return createConfig(EmailSenderConfig.class);
  }
}
