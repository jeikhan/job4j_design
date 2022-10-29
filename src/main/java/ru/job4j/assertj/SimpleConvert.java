package ru.job4j.assertj;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задача. Создать тесты на методы toArray(), toList(),
 * toSet(), toMap(): проверить содержимое коллекции
 * несколькими утверждениями.
 *
 * @author Evgeniy Kapaev
 * @since 29.10.2022
 */
public class SimpleConvert {

    /**
     * Конвертация произвольного числа аргументов в строчный массив.
     *
     * @param example строчные аргументы.
     * @return массив строчных аргументов.
     */
    public String[] toArray(String... example) {
        return example;
    }

    /**
     * Конвертация произвольного числа аргументов в коллекцию List.
     *
     * @param example строчные аргументы.
     * @return коллекция List.
     */
    public List<String> toList(String... example) {
        return Arrays.stream(example).toList();
    }

    /**
     * Конвертация произвольного числа аргументов в коллекцию Set.
     *
     * @param example строчные аргументы.
     * @return коллекция Set.
     */
    public Set<String> toSet(String... example) {
        return Arrays.stream(example).collect(Collectors.toSet());
    }

    /**
     * Конвертация произвольного числа аргументов в коллекцию Map.
     *
     * @param example строчные аргументы.
     * @return коллекция Map.
     */
    public Map<String, Integer> toMap(String... example) {
        return Stream.iterate(0, i -> i < example.length, i -> i + 1)
                .collect(Collectors.toMap(i -> example[i], i -> i, (s1, s2) -> s1));
    }
}
