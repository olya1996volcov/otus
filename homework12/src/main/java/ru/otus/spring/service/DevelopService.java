package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.App;
import ru.otus.spring.domain.TechnicalTask;

@Service
@Slf4j
public class DevelopService {

    public App developingApp(TechnicalTask task) throws InterruptedException {
        log.info(task.getItemName() + ": in progress - Analytic, Develop, Testing, Deploy");
        Thread.sleep(1000);
        log.info(task.getItemName() + ": DONE - Analytic, Develop, Testing, Deploy");
        return new App(task.getItemName());
    }

    public App refactoringApp(App app) throws InterruptedException {
        log.info( app.getName() + ": Refactoring in progress");
        Thread.sleep(1000);
        log.info(app.getName() + ": Refactoring is DONE");
        return app;
    }
}
