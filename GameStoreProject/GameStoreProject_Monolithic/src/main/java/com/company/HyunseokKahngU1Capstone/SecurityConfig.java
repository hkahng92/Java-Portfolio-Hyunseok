package com.company.HyunseokKahngU1Capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception{
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                //authorization for Console
                .mvcMatchers(HttpMethod.PUT, "/console/{id}").hasAuthority("ROLE_STAFF")
                .mvcMatchers(HttpMethod.PUT, "/console/{id}").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.PUT, "/console/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/console").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/console").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/console/{id}").hasAuthority("ROLE_ADMIN")
                //authorization for Games
                .mvcMatchers(HttpMethod.PUT, "/game/{id}").hasAuthority("ROLE_STAFF")
                .mvcMatchers(HttpMethod.PUT, "/game/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/game/{id}").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/game").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/game").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/game/{id}").hasAuthority("ROLE_ADMIN")
                //authorization for T Shirt
                .mvcMatchers(HttpMethod.PUT, "/tShirt/{id}").hasAuthority("ROLE_STAFF")
                .mvcMatchers(HttpMethod.PUT, "/tShirt/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/tShirt/{id}").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/tShirt").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/tShirt").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/tShirt/{id}").hasAuthority("ROLE_ADMIN")

                .anyRequest().permitAll();


        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone").deleteCookies("JSESSIONID").deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
}
