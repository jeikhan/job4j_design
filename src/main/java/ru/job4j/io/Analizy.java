package ru.job4j.io;

import java.io.*;

/**
 * Реализовать метод unavailable(), который
 * анализирует время доступности сервера из
 * server.log и результат записывает в файл
 * unavailable.csv, в виде диапазонов
 * работы сервера по кодам состояния
 * 500 и 400.
 *
 * @author Evgeniy Kapaev
 * @since 05.10.2022
 */
public class Analizy {

    /**
     * Поиск диапазонов работы сервера из log-файла
     * и запись результатов в csv-файл.
     *
     * @param source расположение log-файла.
     * @param target расположение csv-файла.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            StringBuilder result = new StringBuilder();
            boolean flag = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if ((line.startsWith("400") || line.startsWith("500")) && flag) {
                    result.append(line.split(" ")[1]).append(" - ");
                    flag = false;
                } else if ((line.startsWith("200") || line.startsWith("300")) && !flag) {
                    result.append(line.split(" ")[1]).append(";").append(System.lineSeparator());
                    flag = true;
                }
            }
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)))) {
                out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        analizy.unavailable(source, target);
    }
}