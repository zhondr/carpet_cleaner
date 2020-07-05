package kz.nu.carpet_cleaner.register.configurations.beans.all;

import kz.nu.carpet_cleaner.register.dao.BeanScannerDao;
import kz.nu.carpet_cleaner.register.hotconfig.BeanScannerHotConfig;
import kz.nu.carpet_cleaner.register.register.impl.BeanScannerRegister;
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
