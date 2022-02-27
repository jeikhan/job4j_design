package ru.job4j.linkedlist;

/**
 * Реализовать очередь на двух стеках.
 *
 * @author Evgenii Kapaev
 * @since 27.02.2022
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int inCount = 0;
    int outCount = 0;

    /**
     * Возвращение первого значения и удаление его из очереди.
     *
     * @return удаляемое значение.
     */
    public T poll() {
        while (inCount > 0) {
            out.push(in.pop());
            inCount--;
            outCount++;
        }
        T value = out.pop();
        outCount--;
        while (outCount > 0) {
            in.push(out.pop());
            outCount--;
            inCount++;
        }
        return value;
    }

    /**
     * Вставка значения элемента в конец очереди.
     *
     * @param value значение элемента.
     */
    public void push(T value) {
        in.push(value);
        inCount++;
    }
}
