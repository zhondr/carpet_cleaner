package kz.nu.carpet_cleaner.register.configurations.beans.all;

import kz.nu.carpet_cleaner.register.dao.BeanScannerDao;
import kz.nu.carpet_cleaner.register.hotconfig.BeanScannerHotConfig;
import kz.nu.carpet_cleaner.register.register.impl.BeanScannerRegister;
import kz.nu.carpet_cleaner.register.scheduler.BeanScannerScheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    BeanScannerDao.class,
    BeanScannerRegister.class,
    BeanScannerHotConfig.class,
    DataSourceConfiguration.class,
    BeanScannerScheduler.class
})
public class BeanScannerApp {}
