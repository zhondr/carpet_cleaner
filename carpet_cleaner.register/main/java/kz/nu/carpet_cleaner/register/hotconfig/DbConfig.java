package kz.nu.carpet_cleaner.register.hotconfig;


import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;
import kz.greetgo.conf.hot.FirstReadEnv;

@Description("Параметры доступа к БД (используется только БД Postgresql)")
public interface DbConfig {

  @Description("URL доступа к БД")
  @DefaultStrValue("jdbc:postgresql://localhost:25432/carpet_cleaner")
  @FirstReadEnv("CARPET_CLEANER_DB_URL")
  String url();

  @Description("Пользователь для доступа к БД")
  @DefaultStrValue("carpet_cleaner")
  @FirstReadEnv("CARPET_CLEANER_DB_USERNAME")
  String username();

  @Description("Пароль для доступа к БД")
  @DefaultStrValue("111")
  @FirstReadEnv("CARPET_CLEANER_DB_PASSWORD")
  String password();


}
