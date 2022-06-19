package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация пользовательского интерфейса Map.
 *
 * @author Evgeniy Kapaev
 * @since 18.05.2022
 */
public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Вычисление хеш-функции записи.
     *
     * @param hashCode ключ записи.
     * @return хеш-код записи.
     */
    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : Math.abs(hashCode ^ (hashCode >>> 16));
    }

    /**
     * Назначение индекса элементу.
     *
     * @param key ключ.
     * @return индекс элемента.
     */
    private int indexFor(K key) {
        int h = key == null ? 0 : hash(key.hashCode());
        return h % capacity;
    }

    /**
     * Расширение хеш-таблицы.
     */
    private void expand() {
        this.capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                int newIndex = indexFor(kvMapEntry.key);
                newTable[newIndex] = kvMapEntry;
            }
        }
        table = newTable;
    }

    /**
     * Добавление записи в Map.
     *
     * @param key   ключ записи.
     * @param value значение записи.
     * @return если добавлено true, нет - false.
     */
    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int i = indexFor(key);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            float loadFactor = (float) count / capacity;
            if (Float.compare(loadFactor, LOAD_FACTOR) >= 0) {
                expand();
            }
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    /**
     * Возврат значения записи по заданному ключу.
     *
     * @param key ключ записи.
     * @return значение записи.
     */
    @Override
    public V get(K key) {
        int i = indexFor(key);
        return table[i] == null
                || table[i].key.hashCode() == key.hashCode()
                && !key.equals(table[i].key) ? null : table[i].value;
    }

    /**
     * Удаление записи из Map по заданному ключу.
     *
     * @param key ключ записи.
     * @return если удалено true, нет - false.
     */
    @Override
    public boolean remove(K key) {
        int i = indexFor(key);
        boolean result = table[i] != null
                && table[i].key.hashCode() == key.hashCode()
                && key.equals(table[i].key);
        if (result) {
            table[i] = null;
            modCount++;
            count--;
        }
        return result;
    }

    /**
     * Возвращает итератор для элементов.
     *
     * @return итератор.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            /**
             * Проверка наличия последующего элемента
             * в массиве.
             *
             * @return true or false
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                        cursor = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            /**
             * Считывание последующего элемента массива.
             * Иначе генерируется исключение.
             *
             * @return последующий элемент массива
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    /**
     * Вложенный класс модели записи.
     *
     * @param <K> ключ записи.
     * @param <V> значение записи.
     */
    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
