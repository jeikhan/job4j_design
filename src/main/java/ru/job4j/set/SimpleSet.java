package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Задача: Реализовать коллекцию SimpleSet.
 * Коллекция не должна хранить дубликаты.
 * Для хранения использовать SimpleArrayList из прошлого задания.
 *
 * @author Evgenii Kapaev
 * @since 05.04.2022
 */
public class SimpleSet<T> implements Set<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(1);

    /**
     * Добавление элемента в коллекцию.
     *
     * @param value значение элемента.
     * @return true or false.
     */
    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }

    /**
     * Проверка наличия элемента в коллекции.
     *
     * @param value значение элемента.
     * @return true or false.
     */
    @Override
    public boolean contains(T value) {
        boolean result = false;
        while (iterator().hasNext()) {
            if (Objects.equals(iterator().next(), value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
