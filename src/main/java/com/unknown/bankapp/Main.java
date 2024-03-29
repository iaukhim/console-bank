package com.unknown.bankapp;

import com.unknown.bankapp.services.AtmBalanceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.unknown.bankapp.runner.RunnerClass;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        RunnerClass runner = appContext.getBean(RunnerClass.class);
        runner.run();
    }

}
