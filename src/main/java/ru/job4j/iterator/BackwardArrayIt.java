package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Задача. Реализовать итератор для массива.
 * Он должен отдавать элементы в обратном порядке.
 *
 * @author Evgenii Kapaev
 * @since 23.09.21
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    /**
     * Метод возвращает true если есть последующий элемент
     * в массиве.
     *
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    /**
     * Метод возвращает последующий элемент массива.
     * Иначе генерирует исключение.
     *
     * @return последующий элемент массива
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return data[point--];
        }
    }
}
