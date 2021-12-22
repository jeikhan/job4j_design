package ru.job4j.list;

import java.util.*;

/**
 * Задача. Создать реализацию списка на основе динамического
 * массива, аналог ArrayList.
 *
 * @author Evgenii Kapaev
 * @since
 */
public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Добавляет значение в массив.
     *
     * @param value значение.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            int newCapacity = size * 2;
            container = Arrays.copyOf(container, newCapacity);
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Задает новое значение в указанном индексе.
     *
     * @param index    индекс старого значения в массиве.
     * @param newValue новое значение.
     * @return старое значение.
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Удаляет значение в указанном индексе.
     *
     * @param index индекс удаляемого значения.
     * @return удаляемое значение.
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        System.arraycopy(
                container, index + 1,
                container, index,
                size - index - 1
        );
        container[size--] = null;
        modCount++;
        return oldValue;
    }

    /**
     * Возвращает значение в указанном индексе.
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
     * Возвращает размер массива.
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
             * Возвращает true если есть последующий элемент
             * в массиве.
             *
             * @return true or false
             */
            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            /**
             * Метод возвращает последующий элемент массива.
             * Иначе генерирует исключение.
             *
             * @return последующий элемент массива
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[cursor++];
            }
        };
    }
}
