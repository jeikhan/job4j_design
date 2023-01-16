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
        search.validate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Валидация параметров запуска программы.
     *
     * @param args параметры запуска.
     */
    public void validate(String... args) {
        if (!(args.length == 2)) {
            throw new IllegalArgumentException("Передано недостаточное количество аргументов: укажите директорию и расширение искомого файла(-ов).");
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException("Указанный путь не существует в системе.");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Укажите название директории поиска.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Расширение должно начинаться с точки(.)");
        }
        if (!(args[1].length() > 1)) {
            throw new IllegalArgumentException("Расширение не может содержать только точку(.)");
        }
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
