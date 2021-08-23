package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceImpl;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        IOService service = context.getBean(IOServiceImpl.class);
        service.printQuestions();
    }
}
