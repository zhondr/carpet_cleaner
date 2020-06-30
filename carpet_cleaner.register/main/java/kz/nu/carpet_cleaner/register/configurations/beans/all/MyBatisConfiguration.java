package kz.nu.carpet_cleaner.register.configurations.beans.all;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("kz.greetgo.aix_service_bus.register.dao")
public class MyBatisConfiguration {
}
