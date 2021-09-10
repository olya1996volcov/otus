package ru.otus.spring.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ReaderCsvFileTest {
    @Test
    void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/file.csv"));
        String line;
        for (int i = 0; i < 5; i++) {
            line = reader.readLine();
            assertNotNull(line);
        }
    }
}
