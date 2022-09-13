package com.example.GoCheeta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ServiceUserDetails serviceUserDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index","/login", "/driverLogin", "/adminHome", "/adminLogin", "/register","/adminDriver","/adminAddVehicles","/adminManageUsers", "/users","/adminManageVehicle","/adminManageBooking", "/users/add", "/User/edit/{id}", "/User/delete/{id}","/adminUpdateUsers").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error= true")
                .defaultSuccessUrl("/customerHome")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .and()
                .csrf()
                .disable();

        http.headers().frameOptions().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceUserDetails);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

