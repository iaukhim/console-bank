package com.unknown.bankapp;

import com.unknown.bankapp.util.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.unknown.bankapp.runner.RunnerClass;
import com.unknown.bankapp.services.AuthService;


@Configuration
@ComponentScan
public class BeanConfig {

    @Autowired
    AuthService authService;

    @Bean
    public RunnerClass runnerClass(){
        return new RunnerClass(utilClass());
    }

    @Bean
    public UtilClass utilClass(){
        return new UtilClass(authService);
    }
}
