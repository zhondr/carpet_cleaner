package kz.nu.carpet_cleaner.register.configuration;

import kz.nu.carpet_cleaner.register.configurations.beans.all.AppBeanScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({
    AppBeanScanner.class,
})
@ComponentScan(basePackages = "kz.nu.carpet_cleaner.register")
public class ScannerForTests {}
