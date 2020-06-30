//package kz.greetgo.aix_service_bus.register._preparation_.beans;
//
//
//import com.zaxxer.hikari.HikariDataSource;
//import java.io.File;
//import java.nio.file.Files;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import javax.sql.DataSource;
//import kz.greetgo.aix_service_bus.register.configurations.AppFolderPath;
//import kz.greetgo.aix_service_bus.register.configurations.beans.all.AllConfigFactory;
//import kz.greetgo.aix_service_bus.register.hotconfig.DbConfig;
//import kz.greetgo.conf.hot.DefaultStrValue;
//import kz.greetgo.conf.sys_params.SysParams;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DbPreparation {
//
//  public void prepareFolderD() throws Exception {
////    log().info(() -> "Prepare folder D");
//
//    AppFolderPath.do_not_run_liquibase().toFile().getParentFile().mkdirs();
//    AppFolderPath.do_not_run_liquibase().toFile().createNewFile();
//  }
//
//  @Autowired
//  private HikariDataSource dataSource;
//
//  @Autowired
//  private AllConfigFactory allConfigFactory;
//
//  @Autowired
//  protected DbConfig dbConfig;
//
//
//  public void prepareDbConfig() throws Exception {
////    log().info(() -> "Prepare DB Config");
//
//    String defaultUrlValue = DbConfig.class.getMethod("url").getAnnotation(DefaultStrValue.class).value();
//    if (defaultUrlValue.equals(dbConfig.url())) {
//
//      Map<String, String> params = new HashMap<>();
//
//      params.put("url", changeUrlDbName(SysParams.pgAdminUrl(),
//                                        System.getProperty("user.name") + "_" + AppFolderPath.projectName()));
//      params.put("username", System.getProperty("user.name") + "_" + AppFolderPath.projectName());
//      params.put("password", AppFolderPath.projectName() + "-111");
//
//      File file = allConfigFactory.storageFileFor(DbConfig.class);
//
//      List<String> configLines = Files.readAllLines(file.toPath());
//
//      Files.write(file.toPath(), configLines
//          .stream()
//          .map(line -> replaceParameterOrReturnSame(line, params))
//          .collect(Collectors.toList()));
//
////      appConfigFactory.reset();
////      masterDatabaseAccessFactory.reset();
//    }
//  }
//
//  public void dropDb() {
////    log().info(() -> "Drop DB");
//
//    dataSource.close();
//
//    exec("drop database " + extractDbName(dbConfig.url()));
//    exec("drop user " + dbConfig.username());
//  }
//
//  public void createDb() {
////    log().info(() -> "Create DB");
//
////    ConnectParams params = getConnectParams(kind.connection());
//
//    exec("create user " + dbConfig.username() + " with password '" + dbConfig.password() + "'");
//    exec("create database " + extractDbName(dbConfig.url()) + " with owner " + dbConfig.username());
//
//    dataSource.reset();
//  }
//
//  @Autowired
//  private LiquibaseManager liquibaseManager;
//
//  public void applyLiquibaseToOperative() throws Exception {
////    log().info(() -> "Apply Liquibase to DB");
//    liquibaseManager.apply();
//  }
//
//
//  public static String changeUrlDbName(String url, String dbName) {
//    int idx = url.lastIndexOf('/');
//    return url.substring(0, idx + 1) + dbName;
//  }
//
//  public static String extractDbName(String url) {
//    int idx = url.lastIndexOf('/');
//    return url.substring(idx + 1);
//  }
//
//  public static String replaceParameterOrReturnSame(String line, Map<String, String> params) {
//    int i = line.indexOf("=");
//    if (i < 0) {
//      return line;
//    }
//
//    String key = line.substring(0, i).trim();
//
//    String newValue = params.get(key);
//    if (newValue != null) {
//      return key + "=" + newValue;
//    }
//
//    return line;
//  }
//
//  protected Connection connectTo() throws Exception {
//    Class.forName("org.postgresql.Driver");
//    return DriverManager.getConnection(dbConfig.url(), dbConfig.username(), dbConfig.password());
//  }
//
//  protected void exec(String sql) {
//    try {
//
//      try (Connection con = connectTo()) {
//        try (Statement statement = con.createStatement()) {
////          log().info(() -> "Exec SQL: " + sql.replace('\n', ' '));
//          statement.execute(sql);
//        }
//      }
//
//    } catch (RuntimeException e) {
//      throw e;
//    } catch (SQLException e) {
////      log().errorMessage(e.getMessage());
//      throw new RuntimeException(e);
//    }
//  }
//
//
//}
