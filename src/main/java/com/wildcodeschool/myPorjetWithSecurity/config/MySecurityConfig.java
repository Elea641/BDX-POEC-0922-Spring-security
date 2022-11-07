package com.wildcodeschool.myPorjetWithSecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("123"))
                .roles("")
                .and()
                .withUser("Steve")
                .password(encoder.encode("assemble"))
                .roles("CHAMPION")
                .and()
                .withUser("Nick")
                .password(encoder.encode("flerken"))
                .roles("DIRECTOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/secret-bases").hasRole("DIRECTOR")
                .antMatchers("/avengers/assemble").hasRole("CHAMPION")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
