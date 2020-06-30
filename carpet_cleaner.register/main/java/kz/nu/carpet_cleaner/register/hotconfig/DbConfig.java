package kz.nu.carpet_cleaner.register.hotconfig;


import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры доступа к БД (используется только БД Postgresql)")
public interface DbConfig {

  @Description("URL доступа к БД")
  @DefaultStrValue("jdbc:postgres:host:5432/db_name")
  String url();

  @Description("Пользователь для доступа к БД")
  @DefaultStrValue("Some_User")
  String username();

  @Description("Пароль для доступа к БД")
  @DefaultStrValue("Secret")
  String password();


  @Description("URL доступа к БД Wordpress")
  @DefaultStrValue("jdbc:mysql://localhost:3306/db_name_wordpress")
  String wordPressUrl();

  @Description("Пользователь для доступа к БД Wordpress")
  @DefaultStrValue("Some_User_Wordpress")
  String wordPressUsername();

  @Description("Пароль для доступа к БД Wordpress")
  @DefaultStrValue("Secret_Wordpress")
  String wordPressPassword();


}
