package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Задача. Создать утилиту для архивации директории.
 * Техническое задание:
 * 1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\
 * 2. В качестве ключа передаётся расширение файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * 4. Запуск проекта.
 * командой "-d=c:\project\job4j\ -e=.class -o=project.zip"
 * 5. Для работы с входными аргументами использовать класс ArgsName.
 * 6. Для архивации использовать классы ZipOutputStream.java.
 * 7. Для тестирования кода в IDEA нужно прописать параметры запуска main метода.
 *
 * @author Evgeniy Kapaev
 * @since 18.02.2023
 */
public class Zip {

    /**
     * Валидация параметров запуска.
     *
     * @param dir     архивируемая директория.
     * @param exclude расширение игнорируемого файла.
     * @param output  имя и расширение конечного архива.
     */
    public static void validate(Path dir, String exclude, File output) {
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException("Указанный путь не существует в системе.");
        }
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Укажите название директории поиска.");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Расширение игнорируемого файла должно начинаться с точки(.)");
        }
        if (!(exclude.length() > 1)) {
            throw new IllegalArgumentException("Расширение игнорируемого файла не может содержать только точку(.)");
        }
        if (!output.getName().endsWith(".zip")) {
            throw new IllegalArgumentException("Укажите расширение архива в формате '.zip'");
        }
    }

    /**
     * Архивация директории.
     *
     * @param sources директория, которую надо архивировать.
     * @param target  заархивированная директорния.
     */
    public void packDirectory(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Архивация файла.
     *
     * @param source файл, который надо архивировать.
     * @param target заархивированный файл.
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Передано недостаточное количество аргументов: укажите архивируемую директорию, расширение игнорируемого файла, имя и расширение zip-архива");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        Path dir = Path.of(argsName.get("d"));
        String exclude = argsName.get("e");
        File output = new File(argsName.get("o"));
        validate(dir, exclude, output);
        List<Path> files = Search.search(dir, path -> !path.toFile().getName().endsWith(exclude));
        zip.packDirectory(files, output);
    }
}
