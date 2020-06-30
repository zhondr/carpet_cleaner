package kz.nu.carpet_cleaner.register.configurations.beans.all.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order()
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final SwaggerAuthenticationProvider swaggerProvider;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/swagger-resources/*", "/swagger-ui.html", "*.html", "/api/v1/swagger.json")
        .hasAuthority("SWAGGER_MANAGER")
        .anyRequest().permitAll()
        .and()
        .csrf().disable();

    http.formLogin().init(http);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(swaggerProvider);
  }

}
