package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceImpl;
import ru.otus.spring.service.TestingService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        TestingService service = context.getBean(TestingService.class);
        service.testUser();
    }
}
