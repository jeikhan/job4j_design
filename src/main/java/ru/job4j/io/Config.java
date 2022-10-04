package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Задача: реализовать методы load(), value()
 * для считывания файла, получения значения.
 *
 * @author Evgeniy Kapaev
 * @since 04.10.2022
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Считывание файла конфигурации.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(e -> !e.contains("#") && e.contains("="))
                    .map(e -> e.split("="))
                    .forEach(e -> values.put(e[0], e[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение значения передаваемого ключа.
     *
     * @param key ключ.
     * @return значение.
     */
    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("Value is empty!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return out.toString();
    }
}