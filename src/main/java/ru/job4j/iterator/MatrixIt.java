package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Задание: реализовать методы hasNext() и next()
 * интерфейса Iterator<E> в двумерном массиве.
 *
 * @author Evgenii Kapaev
 * @since 03.10.21
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Реализация метода hasNext().
     * Метод проверяет наличие последующего
     * элемента в двумерном массиве.
     *
     * @return true or false.
     */
    @Override
    public boolean hasNext() {
        var result = false;
        for (int i = 0; i < data.length; i++) {
            if (data[i].length > 0) {
                result = true;
            }
        }
        while (column == data[row].length && row != data.length - 1) {
            row++;
            column = 0;
        }
        return result;
    }

    /**
     * Реализация метода next().
     * Метод возвращает последующий элемент
     * двумерного массива.
     *
     * @return последующий элемент.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}