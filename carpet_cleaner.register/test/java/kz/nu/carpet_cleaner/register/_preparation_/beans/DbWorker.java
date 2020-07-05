package kz.nu.carpet_cleaner.register._preparation_.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.sys_params.SysParams;
import kz.nu.carpet_cleaner.controller.AppFolderPath;
import kz.nu.carpet_cleaner.register.configurations.beans.all.liquibase.DbParams;
import kz.nu.carpet_cleaner.register.hotconfig.DbConfig;
import org.postgresql.util.PSQLException;

public class DbWorker {

  public void recreate() throws Exception {
    initParams();
    DbParams params = DbParams.readParams();
    killDb(params);
    createDb(params);
  }

  void killDb(DbParams params) throws Exception {
    try (Connection con = getAdminConnection()) {
      execSql(con, "drop database if EXISTS " + extractDbName(params.url));
      execSql(con, "drop owned by " + params.username + " cascade");
      execSql(con, "drop user " + params.username);
    } catch (PSQLException e){
      System.err.println(e);
    }
  }

  void createDb(DbParams params) throws Exception {
    try (Connection con = getAdminConnection()) {
      execSql(con, "create database " + extractDbName(params.url));
      execSql(con, "create user " + params.username + " with password '" + params.password + "'");
      execSql(con, "GRANT ALL ON DATABASE " + extractDbName(params.url) + " TO " + params.username);
    }
  }

  private void createPropertiesFile(File f) throws Exception {
    f.getParentFile().mkdirs();

    PrintStream out = new PrintStream(new FileOutputStream(f), true, "UTF-8");

    String dbName = System.getProperty("user.name").replaceAll("(_?\\W_?)+", "_") + "_" + AppFolderPath.projectName();

    out.println("url=" + changeUrlDbName(SysParams.pgAdminUrl(), dbName));
    out.println("username=" + dbName.toLowerCase() + "_" + AppFolderPath.projectName());
    out.println("password=" + AppFolderPath.projectName() + "-111");

    out.close();
  }

  private void initParams() throws Exception {
    File f = new File(AppFolderPath.confDif() + "/" + DbConfig.class.getSimpleName() + ".hotconfig");
    if (!f.exists()) {
      createPropertiesFile(f);
    } else {
      try (InputStream inputStream = new FileInputStream(
          AppFolderPath.confDif() + "/" + DbConfig.class.getSimpleName() + ".hotconfig")) {
        Properties properties = new Properties();
        properties.load(inputStream);

        String defaultUrlValue = DbConfig.class.getMethod("url").getAnnotation(DefaultStrValue.class).value();
        if (defaultUrlValue.equals(properties.getProperty("url"))) {
          createPropertiesFile(f);
        }

      }
      new File(AppFolderPath.do_not_run_liquibase().toUri()).createNewFile();
    }
  }

  private void execSql(Connection con, String sql) throws SQLException {
    try (Statement stt = con.createStatement()) {
      System.out.println("EXEC SQL: " + sql);
      stt.executeUpdate(sql);
    }
  }

  public static Connection getAdminConnection() throws Exception {
    Class.forName("org.postgresql.Driver");
    return DriverManager.getConnection(
        SysParams.pgAdminUrl(),
        SysParams.pgAdminUserid(),
        SysParams.pgAdminPassword());
  }

  public static String extractDbName(String url) {
    int idx = url.lastIndexOf('/');
    return url.substring(idx + 1);
  }


  public static String changeUrlDbName(String url, String dbName) {
    int idx = url.lastIndexOf('/');
    return (url.substring(0, idx + 1) + dbName).toLowerCase();
  }
}
