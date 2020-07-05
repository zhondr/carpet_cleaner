package kz.nu.carpet_cleaner.register.configurations.beans.all.liquibase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import kz.nu.carpet_cleaner.controller.AppFolderPath;
import kz.nu.carpet_cleaner.register.hotconfig.DbConfig;

public class DbParams {

  public String url;
  public String username;
  public String password;

  public String url() {
    return url;
  }


  public static DbParams readParams() throws IOException {
    Properties properties = new Properties();

    try (FileInputStream input = new FileInputStream(
        new File(AppFolderPath.confDif() + "/" + DbConfig.class.getSimpleName() + ".hotconfig"))) {
      properties.load(new InputStreamReader(input, Charset.forName("UTF-8")));

      DbParams ret = new DbParams();
      ret.username = properties.getProperty("username");
      ret.password = properties.getProperty("password");
      ret.url = properties.getProperty("url");
      return ret;
    }
  }

}
