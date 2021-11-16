package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class IOServiceImpl implements IOService {
    private final static Scanner scanner = new Scanner(System.in);

    @Override
    public void printString(String str) {
        System.out.println(str);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
