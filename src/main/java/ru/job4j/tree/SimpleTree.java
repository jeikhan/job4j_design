package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
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
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    /**
     * Инициализация родительского узла.
     *
     * @param root родительский узел.
     */
    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Поиск узла по значению parent и добавление в него
     * дочернего узла со значением child.
     *
     * @param parent значение родительского узла.
     * @param child  значение дочернего узла.
     * @return child есть - false, нет - true.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(child);
        if (parentNode.isPresent() && childNode.isEmpty()) {
            result = parentNode.get().children.add(new Node<>(child));
        }
        return result;
    }

    /**
     * Поиск узла по значению предиката.
     *
     * @param condition предикат.
     * @return найденное значение.
     */
    @Override
    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    /**
     * Поиск узла по значению.
     *
     * @param value значение узла.
     * @return найденное значение.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate((n) -> n.getValue().equals(value));
    }

    /**
     * Проверка дерева на бинарность.
     *
     * @return true or false.
     */
    @Override
    public boolean isBinary() {
        return findByPredicate((n) -> n.getChildren().size() > 2).isEmpty();
    }
}
