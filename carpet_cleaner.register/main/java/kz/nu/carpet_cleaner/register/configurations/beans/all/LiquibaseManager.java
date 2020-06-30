package kz.nu.carpet_cleaner.register.configurations.beans.all;

import java.sql.Connection;
import java.sql.DriverManager;

import kz.nu.carpet_cleaner.register.hotconfig.DbConfig;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LiquibaseManager {

  private final DbConfig dbConfig;

  @SneakyThrows
  public void apply() {

    Class.forName("org.postgresql.Driver");

    try (Connection connection = DriverManager.getConnection(
        dbConfig.url(),
        dbConfig.username(),
        dbConfig.password()
    )) {
      PostgresDatabase database = new PostgresDatabase();
      database.setConnection(new JdbcConnection(connection));
      {
        new Liquibase(
            "liquibase/changelog-master.xml",
            new ClassLoaderResourceAccessor(), database
        ).update("");
      }
    }
  }

}
