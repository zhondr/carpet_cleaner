package kz.nu.carpet_cleaner.register.configurations.beans.all;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

  @Bean("MIGRATION")
  @Primary
  public TaskExecutor migrationTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    executor.setCorePoolSize(4);
    executor.setMaxPoolSize(4);

    executor.setThreadNamePrefix("MIGRATION");

    executor.initialize();

    return executor;
  }

}
