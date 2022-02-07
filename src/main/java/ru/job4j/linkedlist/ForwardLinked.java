package ru.job4j.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Задача: реализовать метод delete для односвязного списка.
 *
 * @author Evgenii Kapaev
 * @since 30.01.22
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /**
     * Вставка значения элемента.
     *
     * @param value значение элемента
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Вставка значения элемента в начало списка.
     *
     * @param value значение элемента.
     */
    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        node.next = head;
        head = node;
    }

    /**
     * Удаление первого элемента из списка.
     *
     * @return удаляемый элемент.
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T tmp = head.value;
        Node<T> next = head.next;
        head.next = null;
        head = next;
        return tmp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            /**
             * Проверка наличия последующего элемента.
             *
             * @return true or false.
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Считывание последующего элемента.
             * Иначе генерируется исключение.
             *
             * @return последующий элемент.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Класс отдельного элемента связанного списка.
     *
     * @param <T> параметр типа элемента.
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
