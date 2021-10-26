package com.usa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SocialApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests(a -> a
                .antMatchers("/api", "/Category/**", "/Motorbike/**",
                        "/Client/**", "/Message/**", "/Reservation/**", 
                        "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(l -> l
                .logoutSuccessUrl("/").permitAll()
                )
                .oauth2Login();
        // @formatter:on
        http.cors().and().csrf().disable();
    }

}
