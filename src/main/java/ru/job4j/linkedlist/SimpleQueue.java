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
        T result = null;
        if (outCount != 0) {
            result = out.pop();
            outCount--;
        } else {
            while (inCount != 1) {
                out.push(in.pop());
                inCount--;
                outCount++;
            }
            result = in.pop();
            inCount--;
        }
        return result;
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
