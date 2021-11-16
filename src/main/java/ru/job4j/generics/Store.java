package ru.job4j.generics;

/**
 * Интерфейс контейнера.
 *
 * @param <T> общий тип.
 * @author Evgenii Kapaev
 * @since 16.11.2021
 */
public interface Store<T extends Base> {
    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
