package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализовать методы hasNext() и next() интерфейса Iterator<E>, чтобы
 * hasNext() возвращал true, только если в массиве есть четные перед указателем,
 * next() - возвращает только четные числа.
 *
 * @author Evgenii Kapaev
 * @since 10.10.21
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Возвращает true если есть последующий элемент.
     *
     * @return true если есть последующий элемент
     */
    @Override
    public boolean hasNext() {
        while (index != data.length && data[index] % 2 != 0) {
            index++;
        }
        return index != data.length && data[index] % 2 == 0;
    }

    /**
     * Возвращает следующий элемент в итерации.
     *
     * @return следующий элемент в итерации
     * @throws NoSuchElementException если нет последующего элемента
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
