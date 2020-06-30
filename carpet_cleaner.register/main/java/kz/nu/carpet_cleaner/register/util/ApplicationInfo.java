package kz.nu.carpet_cleaner.register.util;

import kz.nu.carpet_cleaner.register.Application;

public class ApplicationInfo {

  public static String appVersion() {
    return Application.class.getPackage().getImplementationVersion();
  }

}
