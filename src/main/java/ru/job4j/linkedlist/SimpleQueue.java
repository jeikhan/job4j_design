package ru.job4j.linkedlist;

import java.util.NoSuchElementException;

/**
 * Реализовать очередь на двух стеках.
 *
 * @author Evgenii Kapaev
 * @since 27.02.2022
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Возвращение первого значения и удаление его из очереди.
     *
     * @return удаляемое значение.
     */
    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Вставка значения элемента в конец очереди.
     *
     * @param value значение элемента.
     */
    public void push(T value) {
        in.push(value);
    }
}
