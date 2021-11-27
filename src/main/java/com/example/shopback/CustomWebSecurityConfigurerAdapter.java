package com.example.shopback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select login, password, enabled from users where login=?")
                .authoritiesByUsernameQuery("select login, role from users where login=?")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/cart").hasAuthority("USER")
                .antMatchers("/page").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/products/user/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/orders/user/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/products/**").hasAuthority("ADMIN")
                .antMatchers("/orders/**").hasAuthority("ADMIN")
                .antMatchers("/users/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/page", true)
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();
                http.csrf().disable();
    }
}
