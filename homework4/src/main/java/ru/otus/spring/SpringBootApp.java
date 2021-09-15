package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.service.QuestionUserService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class SpringBootApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringBootApp.class, args);
        QuestionUserService service = ctx.getBean(QuestionUserService.class);
        service.quizStart();
    }
}
