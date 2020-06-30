package kz.nu.carpet_cleaner.register.configurations.beans.all;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

import kz.nu.carpet_cleaner.register.hotconfig.DbConfig;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {

  private final DbConfig dbConfig;

  @Bean
  public DataSource dataSource() {
    HikariDataSource pool = new HikariDataSource();

    pool.setDriverClassName("org.postgresql.Driver");
    pool.setJdbcUrl(dbConfig.url());
    pool.setUsername(dbConfig.username());
    pool.setPassword(dbConfig.password());

    pool.setMinimumIdle(5);

    return pool;
  }


  @Bean("AixMySql")
  public DataSource mysqlDataSource() {

    HikariDataSource pool = new HikariDataSource();

    pool.setDriverClassName("com.mysql.jdbc.Driver");
    pool.setJdbcUrl(dbConfig.wordPressUrl());
    pool.setUsername(dbConfig.wordPressUsername());
    pool.setPassword(dbConfig.wordPressPassword());
    pool.setMaximumPoolSize(3);

    return pool;
  }


  @Bean("aix")
  public JdbcTemplate aixJdbcTemplate() {
    return new JdbcTemplate(mysqlDataSource());
  }

  @Primary
  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    return sessionFactory.getObject();
  }


}
