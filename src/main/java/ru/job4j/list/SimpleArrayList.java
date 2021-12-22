package ru.job4j.list;

import java.util.*;

/**
 * Задача. Создать реализацию списка на основе динамического
 * массива, аналог ArrayList.
 *
 * @author Evgenii Kapaev
 * @since 22.12.2021
 */
public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Расширение массива в два раза.
     */
    private void resize() {
        int newCapacity = size * 2;
        container = Arrays.copyOf(container, newCapacity);
    }

    /**
     * Добавление значения в массив.
     *
     * @param value значение.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            resize();
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Запись нового значения в указанный индекс.
     *
     * @param index    индекс старого значения в массиве.
     * @param newValue новое значение.
     * @return старое значение.
     */
    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Удаление значения в указанном индексе.
     *
     * @param index индекс удаляемого значения.
     * @return удаляемое значение.
     */
    @Override
    public T remove(int index) {
        T oldValue = get(index);
        System.arraycopy(
                container, index + 1,
                container, index,
                size - index - 1
        );
        container[--size] = null;
        modCount++;
        return oldValue;
    }

    /**
     * Считывание значения в указанном индексе.
     *
     * @param index индекс значения.
     * @return значение в указанном индексе.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Считывание размера массива.
     *
     * @return размер массива.
     */
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private int expectedModCount = modCount;

            /**
             * Проверка наличия последующего элемента
             * в массиве.
             *
             * @return true or false
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            /**
             * Считывание последующего элемента массива.
             * Иначе генерируется исключение.
             *
             * @return последующий элемент массива
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
