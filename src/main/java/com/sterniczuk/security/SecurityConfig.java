package com.sterniczuk.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.time.LocalDate;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("generuję hasło: " + passwordEncoder.encode("zaq1@WSX"));
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("Select NIP, " +
                        "password, idStatus from Users where NIP = ?")
        .authoritiesByUsernameQuery("Select NIP, 'ROLE_USER' from Users where NIP=?");




    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/user/**")
                     .access("hasRole('ROLE_USER')")
                        .antMatchers("/","/**").permitAll()
                            .antMatchers("/h2-console/**").permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .usernameParameter("NIP")
                .defaultSuccessUrl("/user/ksiega")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/login");
            http
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                    .ignoringAntMatchers("/email/**")
                    .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin();


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
