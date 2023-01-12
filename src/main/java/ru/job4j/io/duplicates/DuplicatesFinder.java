package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * description
 *
 * @author Evgeniy Kapaev
 * @since 18.11.2022
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("data/duplicates/"), duplicatesVisitor);
        duplicatesVisitor.getDuplicateFiles();
    }
}
