package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Каркас универсального хранилища.
 *
 * @author Evgenii Kapaev
 * @since 16.11.2021
 */
public final class MemStore<T extends Base> implements Store<T> {
    private Map<String, T> mem = new HashMap<>();

    /**
     * Добавляет модель в хранилище.
     *
     * @param model модель.
     */
    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    /**
     * Заменяет модель по заданному id.
     *
     * @param id    id модели.
     * @param model модель.
     * @return true or false.
     */
    @Override
    public boolean replace(String id, T model) {
        return mem.replace(id, mem.get(id), model);
    }

    /**
     * Удаляет модель по заданному id.
     *
     * @param id id модели.
     * @return true or false.
     */
    @Override
    public boolean delete(String id) {
        return mem.remove(id, mem.get(id));
    }

    /**
     * Находит модель в хранилище по
     * заданному id.
     *
     * @param id id модели.
     * @return true or false.
     */
    @Override
    public T findById(String id) {
        return mem.getOrDefault(id, null);
    }
}
