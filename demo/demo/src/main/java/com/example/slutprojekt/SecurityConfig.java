package com.example.slutprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private StudentRepo studentRepo;




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/home").hasRole("STUDENT")
                .antMatchers("/style.css").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.formLogin()
                //.defaultSuccessUrl("/secret", false);
                .formLogin().defaultSuccessUrl("/", true)
                //.loginPage("/login")
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
