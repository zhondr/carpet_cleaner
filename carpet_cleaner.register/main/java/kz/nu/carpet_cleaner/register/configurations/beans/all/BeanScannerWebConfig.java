package kz.nu.carpet_cleaner.register.configurations.beans.all;

import kz.nu.carpet_cleaner.controller.controller.BeanScannerController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BeanScannerApp.class, BeanScannerController.class})
public class BeanScannerWebConfig {
}
