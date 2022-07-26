package ru.job4j.io;

import java.io.FileInputStream;

/**
 * Задача: создать программу для чтения
 * данных из файла.
 *
 * @author Evgeniy Kapaev
 * @since 26.07.2022
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("./data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int rsl = Integer.parseInt(line);
                if (rsl % 2 == 0) {
                    System.out.println(rsl + " - четное");
                } else {
                    System.out.println(rsl + " - нечетное");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
