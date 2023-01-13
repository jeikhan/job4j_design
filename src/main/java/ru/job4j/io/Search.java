package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Задание. Реализовать программу для поиска файлов,
 * соответствующих заданному условию предиката (например,
 * поиск файлов по определенному расширению и т.п.).
 *
 * @author Evgeniy Kapaev
 * @since 13.11.2022
 */
public class Search {

    /**
     * Запуск программы.
     */
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        if (search.validate(args)) {
            throw new IllegalArgumentException("Укажите root-папку и расширение файла!");
        }
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Валидация параметров запуска программы.
     *
     * @param arguments параметры запуска.
     * @return true если количество удовлетворяет условиям поиска,
     * false если параметров недостаточно.
     */
    public boolean validate(String... arguments) {
        return arguments.length != 2;
    }

    /**
     * Поиск файлов по заданному предикату condition.
     *
     * @param root      путь к файлам.
     * @param condition предикат.
     * @return список файлов, соответствующих предикату.
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
