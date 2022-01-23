package ru.job4j.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Задача: создать контейнер на базе связанного списка с методами:
 * add(E value) - добавляет элемент в конец списка;
 * E get(int index) - чтение значения найденного Node;
 * реализовать интерфейс Iterable<E>.
 *
 * @author Evgenii Kapaev
 * @since 23.01.22
 */
public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private int modCount;
    private Node<E> first = null;
    private Node<E> last = null;

    /**
     * Класс отдельного элемента связанного списка.
     *
     * @param <E> параметр типа элемента.
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> prev, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Вставка значения элемента.
     *
     * @param value значение элемента
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null, null);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    /**
     * Чтение значения найденного элемента.
     *
     * @param index индекс элемента.
     * @return значение элемента.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> node;
            private int cursor = 0;
            private final int expectedModCount = modCount;

            /**
             * Проверка наличия последующего элемента.
             *
             * @return true or false.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            /**
             * Считывание последующего элемента.
             * Иначе генерируется исключение.
             *
             * @return последующий элемент.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (cursor != 0) {
                    node = node.next;
                } else {
                    node = first;
                }
                cursor++;
                return node.item;
            }
        };
    }
}
