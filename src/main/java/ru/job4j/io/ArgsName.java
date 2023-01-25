package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Задача. Написать программу, которая принимает массив параметров
 * и разбивает их на пары "ключ, значение".
 *
 * @author Evgeniy Kapaev
 * @since 18.01.2023
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    /**
     * Получение значения из передаваемого ключа. При отсутствии ключа -
     * падает с исключением IllegalArgumentException("Запрашиваемый ключ не найден")
     *
     * @param key передаваемый ключ.
     * @return значение заданного ключа.
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Запрашиваемый ключ не найден");
        }
        return values.get(key);
    }

    /**
     * Проверка входных параметров на соответствие шаблону.
     * Если в аргументе отсутствует ключ - падает исключение IllegalArgumentException("В аргументе отсутствует ключ").
     * Если в аргументе отсутствует значение - падает исключение IllegalArgumentException("В аргументе отсутствует значение").
     * Если в аргументе неверно указано значение - падает исключение IllegalArgumentException("Неверно указан аргумент значения").
     * Если в аргументе не указано значение - падает исключение IllegalArgumentException("Неверно указан аргумент ключа").
     *
     * @param args передаваемые аргументы.
     */
    private void validateArgs(String args) {
        if (args.startsWith("-=")) {
            throw new IllegalArgumentException("В аргументе отсутствует ключ");
        }
        if (args.indexOf("=") == (args.length() - 1)) {
            throw new IllegalArgumentException("В аргументе отсутствует значение");
        }
        if (!args.contains("=")) {
            throw new IllegalArgumentException("Неверно указан аргумент значения");
        }
        if (!args.startsWith("-")) {
            throw new IllegalArgumentException("Неверно указан аргумент ключа");
        }
    }

    /**
     * Парсинг аргументов.
     * Проверка аргументов на валидность, приведение аргументов к виду "ключ, значение"
     * и добавление их в коллекцию values.
     *
     * @param args передаваемые аргументы.
     */
    private void parse(String[] args) {
        for (String argument : args) {
            validateArgs(argument);
            String[] tempArray = argument.replaceFirst("-", "").split("=", 2);
            values.put(tempArray[0], tempArray[1]);
        }
    }

    /**
     * Возвращает спарсенные аргументы.
     * При отсутствии аргументов - падает исключение IllegalArgumentException("Аргументы отсутствуют").
     *
     * @param args передаваемые аргументы.
     * @return спарсенные аргументы.
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Аргументы отсутствуют");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
