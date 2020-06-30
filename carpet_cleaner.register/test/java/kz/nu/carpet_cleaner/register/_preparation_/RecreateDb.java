package kz.greetgo.aix_service_bus.register._preparation_;

import kz.greetgo.aix_service_bus.register._preparation_.beans.DbWorker;
import kz.greetgo.aix_service_bus.register.configurations.beans.all.liquibase.PostgresLiquibase;

public class RecreateDb {

  public static void main(String[] args) throws Exception {
    new DbWorker().recreate();
    new PostgresLiquibase().apply(false);
  }

}
