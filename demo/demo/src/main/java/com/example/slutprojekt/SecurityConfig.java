package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/", "/home", "/init", "/h2", "/h2/**").permitAll()

                .antMatchers("/style.css").permitAll()
                .antMatchers("/files").permitAll()
                .antMatchers("/images/**").permitAll()
                //.antMatchers("/video").permitAll() // Nödlösning när security inte funkar
                .antMatchers( "/init", "/h2", "/h2/**").permitAll()
                .antMatchers( "/login").permitAll()
                //.antMatchers("/", "/home").hasRole("USER") // Kommenterade ut eftersom inlogg inte funkade
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                //.formLogin()
                .formLogin().defaultSuccessUrl("/", true)
                .loginPage("/login")
                //and().formLogin().disable()
                .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user").password("123").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("AndreasO").password("123").roles("USER","ADMIN").build());
        return manager;
    }

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    // configure the authentication provider - the one we declare in the next method
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    // sets the object userDetailsService as the UserDetailsService in Spring Security, and sets the encoder (the same one that the InitController uses)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    // makes it possible to get the encoder autowired in InitController (to be able to encode the password when saving the user
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }




    //@Autowired
    //private StudentRepo studentRepo;




    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/home").authenticated()
                .antMatchers("/style.css").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.formLogin()
                //.defaultSuccessUrl("/secret", false);
                .formLogin().defaultSuccessUrl("/", true)
                .loginPage("/login")
                .permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService(StudentRepo studentRepo) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        for (Student student : studentRepo.findAll()) {
            manager.createUser(User.withDefaultPasswordEncoder()
                    .username(student.getEmail()).password(student.getPassword()).roles("STUDENT").build());}
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("student").password("123").roles("STUDENT").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("teacher").password("123").roles("TEACHER").build());
            manager.createUser(User.withDefaultPasswordEncoder()
                    .username("admin").password("123").roles("USER", "ADMIN").build());
            return manager;
        }


    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user").password("123").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin").password("123").roles("USER", "ADMIN").build());
        return manager;
    }*/


}
