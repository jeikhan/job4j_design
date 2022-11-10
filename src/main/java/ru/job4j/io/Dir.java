package ru.job4j.io;

import java.io.File;

/**
 * Задача. Создать программу для вывода всех файлов
 * в каталоге по шаблону: имя файла/каталога (размер).
 *
 * @author Evgeniy Kapaev
 * @since 10.11.2022
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("d://projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("%s (%d bytes)", subfile.getName(), subfile.length()));
        }
    }
}
