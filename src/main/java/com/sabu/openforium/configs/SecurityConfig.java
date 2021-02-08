package com.sabu.openforium.configs;

import com.sabu.openforium.security.AuthService;
import com.sabu.openforium.security.BasicAuthenticationPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;
    @Autowired
    private BasicAuthenticationPoint basicAuthenticationPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(basicAuthenticationPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("sabu").password("hello123")
//                .authorities("ROLE_USER");

        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication()
//                .withUser("sabu").password(passwordEncoder().encode("hello123"))
//                .authorities("ROLE_USER");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled"
//                        + " from users where username=?")
//                .authoritiesByUsernameQuery("select username, authority "
//                        + "from authorities where username=?")
//                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

