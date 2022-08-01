package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Задача: написать программу для чтения
 * Log-файла используя BufferedReader.
 *
 * @author Evgeniy Kapaev
 * @since 29.07.2022
 */
public class LogFilter {
    private List<String> result;

    public List<String> filter(String file) {
        Pattern pattern = Pattern.compile("\\s404\\s(\\d+|-)$");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                    .filter(s -> {
                        Matcher matcher = pattern.matcher(s);
                        return matcher.find();
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("./data/log.txt");
        System.out.println(log);
    }
}
