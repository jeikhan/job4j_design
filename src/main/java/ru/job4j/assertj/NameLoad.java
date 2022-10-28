package ru.job4j.assertj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Задача: написать тесты на методы parse() и
 * getMap() с генерацией всех исключений.
 *
 * @author Evgeniy Kapaev
 * @since 28.10.2022
 */
public class NameLoad {
    private final Map<String, String> values = new HashMap<>();

    /**
     * Парсинг массива аргументов.
     * Если массив аргументов пустой - бросает исключение IllegalArgumentException("Names array is empty").
     *
     * @param names массив аргументов.
     */
    public void parse(String... names) {
        if (names.length == 0) {
            throw new IllegalArgumentException("Names array is empty");
        }
        values.putAll(Arrays.stream(names)
                .map(String::trim)
                .filter(this::validate)
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0],
                        e -> e[1],
                        (first, second) -> String.format("%s+%s", first, second)
                )));
    }

    /**
     * Валидация аргументов.
     * Если значение аргумента не содержит символ "=" - бросает исключение IllegalArgumentException("this name: %s does not contain the symbol "="").
     * Если значение аргумента не содержит имя - бросает исключение IllegalArgumentException("this name: %s does not contain a key").
     * Если значение аргумента не содержит значение - бросает исключение IllegalArgumentException("this name: %s does not contain a value").
     *
     * @param name имя аргумента.
     * @return true.
     */
    private boolean validate(String name) {
        if (!name.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", name));
        }
        if (name.startsWith("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a key", name));
        }
        if (name.indexOf("=") == name.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", name));
        }
        return true;
    }

    /**
     * Добавление значений в коллекцию.
     * Если значение отсутствует - бросает исключение IllegalStateException("collection contains no data").
     *
     * @return значения, содержащиеся в коллекции.
     */
    public Map<String, String> getMap() {
        if (values.isEmpty()) {
            throw new IllegalStateException("collection contains no data");
        }
        return values;
    }
}
