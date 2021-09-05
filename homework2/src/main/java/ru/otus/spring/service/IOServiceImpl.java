package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class IOServiceImpl implements IOService {
    private final static Scanner scanner = new Scanner(System.in);
    private final StringFormatterService stringFormatter;

    @Override
    public void print(String str) {
        System.out.println(str);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

    @Override
    public void printQuestion(Question q) {
        System.out.println(stringFormatter.getQuestion(q));
    }
}
