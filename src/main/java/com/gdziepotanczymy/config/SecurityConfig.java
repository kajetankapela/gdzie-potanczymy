package com.gdziepotanczymy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsAdapter userDetailsAdapter;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsAdapter);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // wyłączenie zabezpieczenia przed CSRF
                .authorizeRequests() // autoryzuj wszystkie żądania
                .antMatchers("/").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/all-events").hasAnyRole("ADMIN", "ANONYMOUS")
                .antMatchers("/all-participant-events").hasRole("PARTICIPANT")
                .antMatchers("/all-organizer-events").hasRole("ORGANIZER")
                .antMatchers("/all-organizers").authenticated()
                .antMatchers("/all-stars").authenticated()
                .antMatchers("/all-participants").hasAnyRole("ADMIN", "ORGANIZER")
                .antMatchers("/all-dance-types").hasAnyRole("ADMIN", "ORGANIZER")
                .antMatchers("/new-participant").permitAll()
                .antMatchers("/new-organizer").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // generowanie formularza logowania
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/"); // dodanie wylogowania pod adresem /logout
    }

    @Override
    public void configure(WebSecurity security) {
        security.ignoring().antMatchers("/assets/**");
    }

}
