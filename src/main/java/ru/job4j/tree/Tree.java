package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Задача: создать элементарную структуру
 * дерева. Метод add - Должен находить узел по
 * значению parent и добавлять в него дочерний
 * узел со значением child.
 *
 * @author Evgeniy Kapaev
 * @since 05.07.2022
 */
public interface Tree<E> {

    /**
     * Поиск узла по значению parent и добавление в него
     * дочернего узла со значением child.
     *
     * @param parent значение родительского узла.
     * @param child  значение дочернего узла.
     * @return child есть - false, нет - true.
     */
    boolean add(E parent, E child);

    /**
     * Поиск узла по значению предиката.
     *
     * @param condition предикат.
     * @return найденное значение.
     */
    Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition);

    /**
     * Поиск узла по значению.
     *
     * @param value значение узла.
     * @return найденное значение.
     */
    Optional<Node<E>> findBy(E value);

    /**
     * Проверка дерева на бинарность.
     *
     * @return true or false.
     */
    boolean isBinary();

    /**
     * Модель узла.
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

        /**
         * Чтение значения узла.
         *
         * @return значение узла.
         */
        public E getValue() {
            return value;
        }

        /**
         * Вывод списка узлов.
         *
         * @return список узлов.
         */
        public List<Node<E>> getChildren() {
            return children;
        }
    }
}
