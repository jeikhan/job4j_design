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
    private int size = 0;

    /**
     * Удаление значения элемента из стэка.
     *
     * @return удаленное значение.
     */
    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    /**
     * Вставка значения элемента в стэк.
     *
     * @param value значение элемента.
     */
    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    /**
     * Проверка стека на наличие элементов.
     *
     * @return true or false.
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
