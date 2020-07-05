package kz.nu.carpet_cleaner.register.configurations.beans.all;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("kz.nu.carpet_cleaner.register.dao")
public class MyBatisConfiguration {
}
