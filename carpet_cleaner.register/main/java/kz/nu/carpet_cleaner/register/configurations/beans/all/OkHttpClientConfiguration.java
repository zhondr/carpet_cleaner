package kz.nu.carpet_cleaner.register.configurations.beans.all;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpClientConfiguration {

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder()
        .readTimeout(15000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .connectionPool(new ConnectionPool(300, 5L, TimeUnit.MINUTES))
        .build();
  }
}
