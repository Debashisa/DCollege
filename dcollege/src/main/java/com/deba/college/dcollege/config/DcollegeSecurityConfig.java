package com.deba.college.dcollege.config;


import com.deba.college.dcollege.service.UserService;
import com.deba.college.dcollege.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class DcollegeSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;


    /*
    With Spring security 5 you have to pass password encoder otherwise you will get unahtorized.
     *//*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test")
                .password(bCryptPasswordEncoder.encode("test"))
                .roles("USER");
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(getEncoder());

        auth.authenticationProvider(daoAuthenticationProvider);

    }

    @Bean
    public BCryptPasswordEncoder getEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /*
    httBasic is required for basic authentication.
    You have to add this if you want user/password validation.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/registration").hasRole("ADMIN")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated().
                and().
                    formLogin().
                        loginPage("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .and().httpBasic();
        httpSecurity.csrf().disable();

    }
    @Override
    public void configure(WebSecurity config){
        config.ignoring().antMatchers(
                "/js/**",
                "/css/**",
                "/img/**",
                "/webjars/**");
    }
}
