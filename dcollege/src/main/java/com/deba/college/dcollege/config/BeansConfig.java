package com.deba.college.dcollege.config;

import com.deba.college.dcollege.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeansConfig {

    @Bean
    @Scope("prototype")
    public User getUser()
    {
        return new User();
    }
}
