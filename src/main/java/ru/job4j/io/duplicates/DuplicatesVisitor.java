package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Задача. Реализовать программу, которая ищет дубликаты файлов в
 * папке и выводит их по шаблону:
 * <p>
 * Имя файла, Размер.<br/>
 * -- Каталоги расположения дубликатов.
 * </p>
 *
 * @author Evgeniy Kapaev
 * @since 18.11.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> list = new HashMap<>();

    /**
     * Фильтрация и вывод списка повторяющихся файлов.
     */
    public void getDuplicateFiles() {
        for (List<Path> item : list.values()) {
            if (item.size() > 1) {
                System.out.println(
                        item.get(0).toFile().getName()
                                + " - "
                                + item.get(0).toFile().length()
                                + "Mb"
                );
                for (Path file : item) {
                    System.out.println(file);
                }
            }
        }
    }

    /**
     * Добавление файлов из директории в list.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        list.putIfAbsent(fileProperty, new ArrayList<>());
        list.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }
}