package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс SearchFiles - подпрограмма для поиска файлов по заданному
 * предикату condition класса Search.
 *
 * @author Evgeniy Kapaev
 * @since 13.11.2022
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    private final Predicate<Path> condition;
    private final List<Path> paths = new ArrayList<>();

    /**
     * Инициализация поля condition, содержащего условие поиска.
     *
     * @param condition предикат.
     */
    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    /**
     * Возвращает список файлов в paths.
     *
     * @return список файлов paths.
     */
    public List<Path> getPaths() {
        return paths;
    }

    /**
     * Проходит по файлам в каталоге и при удовлетворении условия
     * предиката добавляет его в paths.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
