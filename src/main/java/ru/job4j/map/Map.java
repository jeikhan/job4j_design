package ru.job4j.map;

/**
 * Пользовательский интерфейс Map.
 *
 * @author Evgeniy Kapaev
 * @since 18.05.2022
 */
public interface Map<K, V> extends Iterable<K> {
    /**
     * Добавление записи в Map.
     *
     * @param key   ключ элемента.
     * @param value значение элемента.
     * @return если добавлено true, нет - false.
     */
    boolean put(K key, V value);

    /**
     * Возврат значения записи по заданному ключу.
     *
     * @param key ключ элемента.
     * @return значение элемента.
     */
    V get(K key);

    /**
     * Удаление записи из Map по заданному ключу.
     *
     * @param key ключ эелемента.
     * @return если удалено true, нет - false.
     */
    boolean remove(K key);
}
