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
        Path start = Paths.get(".");
        search(start, path -> path.toFile().getName().endsWith(".js")).forEach(System.out::println);
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
