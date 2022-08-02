package ru.job4j.io;

import java.io.*;
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

    /**
     * Фильтрация содержимого log-файла по заданному
     * паттерну.
     *
     * @param file входной файл с данными.
     * @return отфильтрованный список результатов.
     */
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

    /**
     * Сохранение отфильтрованого результата log-файла в
     * отдельный файл.
     *
     * @param log  входной файл с данными.
     * @param file файл с отфильтрованными данными.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("./data/log.txt");
        save(log, "./data/404.txt");
    }
}
