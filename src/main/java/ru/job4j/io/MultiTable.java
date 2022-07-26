package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Задача: написать программу для вывода
 * таблицы умножения в файл.
 *
 * @author Evgeniy Kapaev
 * @since 24.07.2022
 */
public class MultiTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("./data/MultipleTable.txt")) {
            for (int i = 1; i < 10; i++) {
                String row = String.valueOf(i);
                for (int j = 2; j < 10; j++) {
                    int rsl = i * j;
                    row += "\t" + rsl;
                }
                row += System.lineSeparator();
                out.write(row.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
