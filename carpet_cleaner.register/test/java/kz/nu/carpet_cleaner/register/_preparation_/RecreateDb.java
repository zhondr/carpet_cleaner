package kz.nu.carpet_cleaner.register._preparation_;

import kz.nu.carpet_cleaner.register._preparation_.beans.DbWorker;
import kz.nu.carpet_cleaner.register.configurations.beans.all.liquibase.PostgresLiquibase;

public class RecreateDb {

  public static void main(String[] args) throws Exception {
    new DbWorker().recreate();
    new PostgresLiquibase().apply(false);
  }

}
