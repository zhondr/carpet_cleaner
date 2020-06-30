package kz.nu.carpet_cleaner.register.configurations.beans.all;


import kz.greetgo.aix_service_bus.register.hotconfig.DbConfig;
import kz.greetgo.conf.hot.FileConfigFactory;
import kz.nu.carpet_cleaner.controller.AppFolderPath;
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

}
