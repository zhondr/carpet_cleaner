package kz.nu.carpet_cleaner.register.configurations.beans.all.liquibase;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import kz.nu.carpet_cleaner.controller.AppFolderPath;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Kasyanov Maxim on 8/16/2017.
 */
public class PostgresLiquibase {

  private final Log log = LogFactory.getLog(getClass());

  public void apply(boolean needCheckUpgrade) throws Exception {

    DbParams params = DbParams.readParams();
    if (needCheckUpgrade) {
      File checkFile = AppFolderPath.do_not_run_liquibase().toFile();
      if (checkFile.exists()) {
        log.info("Запуск liquibasе отменён, потому что есть файл: " + checkFile);
        return;
      }
    }

    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection(params.url(), params.username, params.password)) {
      PostgresDatabase postgresDatabase = new PostgresDatabase();
      postgresDatabase.setConnection(new JdbcConnection(connection));
      new Liquibase("liquibase/changelog-master.xml", new ClassLoaderResourceAccessor(), postgresDatabase).update("");
    }
  }

}
