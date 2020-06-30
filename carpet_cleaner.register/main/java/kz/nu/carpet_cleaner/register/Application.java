package kz.nu.carpet_cleaner.register;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import kz.nu.carpet_cleaner.controller.AppFolderPath;
import kz.nu.carpet_cleaner.register.configurations.beans.all.LiquibaseManager;
import kz.nu.carpet_cleaner.register.configurations.beans.all.SchedulerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

  @Autowired
  private LiquibaseManager liquibaseManager;

  @Autowired
  private SchedulerConfiguration schedulerConfiguration;

  public static void main(String[] args) {
    System.setProperty("spring.profiles.active", "dev");
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Application.class);
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    servletContext.setInitParameter("spring.profiles.active", "prod");
    super.onStartup(servletContext);
  }

  @PostConstruct
  public void listen() {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    if (!AppFolderPath.do_not_run_liquibase().toFile().exists()) {
      liquibaseManager.apply();
    }
    schedulerConfiguration.startup();

  }

}
