package com.sample.project.serviceprovider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration class for basic auth.
 * @author Sindhu S
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configure basic authentication.
     *
     * @param httpSecurity {@link HttpSecurity}
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
          .csrf().disable()
          .authorizeRequests().anyRequest().authenticated()
          .and()
          .httpBasic();
    }

    /**
     * Configuring the credentials.
     *
     * @param auth {@link AuthenticationManagerBuilder}
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("admin")
          .password("{noop}admin")
          .roles("ADMIN");
    }
}