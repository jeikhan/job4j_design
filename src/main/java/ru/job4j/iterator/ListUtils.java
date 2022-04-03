package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Задача. Написать методы:
 * 1) вставка значения до заданного индекса;
 * 2) вставка значения после заданного индекса;
 * 3) удаление значения по заданному предикату;
 * 4) изменение значения по заданному предикату;
 * 5) удаление всех элементов соответствующих заданному списку.
 *
 * @author Evgenii Kapaev
 * @since 20.03.2022
 */
public class ListUtils {

    /**
     * Вставка значения до заданного индекса.
     *
     * @param list  входящий список значений.
     * @param index индекс значения.
     * @param value значение.
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.add(value);
    }

    /**
     * Вставка значения после заданного индекса.
     *
     * @param list  входящий список значений.
     * @param index индекс значения.
     * @param value значение.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index + 1);
        iterator.add(value);
    }

    /**
     * Удаление значения по заданному предикату.
     *
     * @param list   входящий список значений.
     * @param filter заданный предикат.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Изменение значения по заданному предикату.
     *
     * @param list   входящий список значений.
     * @param filter заданный предикат.
     * @param value  значение.
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Удаление всех элементов соответствующих заданному списку.
     *
     * @param list     входящий список значений.
     * @param elements список удаляемых элементов.
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }
}
