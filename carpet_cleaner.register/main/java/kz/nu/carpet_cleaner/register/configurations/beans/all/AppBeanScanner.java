package kz.nu.carpet_cleaner.register.configurations.beans.all;

import kz.greetgo.aix_service_bus.register.dao.BeanScannerDao;
import kz.greetgo.aix_service_bus.register.hotconfig.BeanScannerHotConfig;
import kz.greetgo.aix_service_bus.register.register.impl.BeanScannerRegister;
import org.springframework.context.annotation.Import;

@Import({
    BeanScannerRegister.class,
    BeanScannerHotConfig.class,
    BeanScannerDao.class,
    MyBatisConfiguration.class,
    DataSourceConfiguration.class,
    AllConfigFactory.class,
    TaskExecutorConfig.class,
})
public class AppBeanScanner {
}
