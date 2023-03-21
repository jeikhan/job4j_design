package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Задание. Создать программу 'Консольный чат':
 * пользователь вводит слово-фразу, программа берет случайную фразу
 * из текстового файла и выводит в ответ;
 * программа замолкает если пользователь вводит слово «стоп», при этом
 * он может продолжать отправлять сообщения в чат;
 * если пользователь вводит слово «продолжить», программа снова начинает отвечать;
 * при вводе слова «закончить» программа прекращает работу;
 * запись диалога, включая слова-команды стоп/продолжить/закончить
 * должны быть записаны в текстовый лог;
 * текстовые файлы с ответами бота и лог размещается в ранее созданном каталоге
 * data в корне проекта.
 *
 * @author Evgeniy Kapaev
 * @since 18.03.2023
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Логика чата.
     * По умолчанию бот отвечает на любые фразы, кроме управляющих:
     * (закончить, стоп, продолжить).
     * При вводе фразы "закончить" - происходит выход из программы.
     * При вводе фразы "стоп" - бот больше не реагирует на фразы.
     * При вводе фразы "продолжить" - бот продолжает реагировать на фразы.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> answerPhrase = readPhrases();
        String answerBot;
        String userInput;
        boolean botFlag = true;
        boolean inputFlag = true;
        System.out.println("(0_0): добро пожаловать в чат-бот!");
        System.out.println("Для взаимодействия с ботом нужно вводить фразы.");
        System.out.println("Чтобы выйти из чата введите 'закончить'.");
        System.out.println("Чтобы остановить диалог с ботом введите 'стоп'.");
        System.out.println("Чтобы продолжить диалог с ботом введите 'продолжить'.");
        while (inputFlag) {
            System.out.print("Введите фразу: ");
            userInput = input.nextLine();
            log.add(userInput);
            if (OUT.equals(userInput)) {
                inputFlag = false;
                botFlag = false;
            }
            if (STOP.equals(userInput)) {
                System.out.println("(-_-): я заснул... z z z...");
                botFlag = false;
            }
            if (botFlag) {
                answerBot = answerPhrase.get((int) (Math.random() * answerPhrase.size()));
                System.out.println("(0_0): " + answerBot);
                log.add(answerBot);
            }
            if (CONTINUE.equals(userInput)) {
                System.out.println("(0_0): я проснулся!");
                botFlag = true;
            }
        }
        System.out.println("(0_0) Возвращайтесь скорее!");
        saveLog(log);
    }

    /**
     * Чтение ботом заранее подготовленных фраз из файла.
     *
     * @return фраза.
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            in.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Сохранения истории чата в лог-файл.
     *
     * @param log история чата.
     */
    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск программы в консоли.
     */
    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/chatLog/chatHistory.txt", "./data/chatLog/botAnswer.txt");
        consoleChat.run();
    }
}
