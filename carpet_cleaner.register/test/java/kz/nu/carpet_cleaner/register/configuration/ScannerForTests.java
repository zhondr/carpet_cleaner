package kz.greetgo.aix_service_bus.register.configuration;

import kz.greetgo.aix_service_bus.register.configurations.beans.all.AppBeanScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({
    AppBeanScanner.class,
})
@ComponentScan(basePackages = "kz.greetgo.aix_service_bus.register")
public class ScannerForTests {}
