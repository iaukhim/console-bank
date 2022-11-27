package com.unknown.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.unknown.bankapp.runner.RunnerClass;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        RunnerClass runner = appContext.getBean(RunnerClass.class);
        runner.run();
    }


}
