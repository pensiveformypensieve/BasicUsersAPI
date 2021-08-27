package com.horcrux.ravenclaw.config;

import com.horcrux.ravenclaw.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.parentAuthenticationManager(authenticationManagerBean());
        auth.getDefaultUserDetailsService();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.cors().and().
                http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/security/authenticate").permitAll()
                .anyRequest().authenticated();
    }
}
