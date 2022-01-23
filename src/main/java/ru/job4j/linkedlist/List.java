package ru.job4j.linkedlist;

/**
 * Интерфейс для связанного списка.
 *
 * @author Evgenii Kapaev
 * @since 23.01.22
 */
public interface List<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}
