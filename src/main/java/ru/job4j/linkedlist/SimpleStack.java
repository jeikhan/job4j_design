package ru.job4j.linkedlist;

/**
 * Задача: реализовать стэк.
 * Методы pop(), push() реализовать на основе методов
 * deleteFirst(), addFirst() класса ForwardLinked.
 *
 * @author Evgenii Kapaev
 * @since 03.02.2022
 */
public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Удаление значения элемента из стэка.
     *
     * @return удаленное значение.
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Вставка значения элемента в стэк.
     *
     * @param value значение элемента.
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}
