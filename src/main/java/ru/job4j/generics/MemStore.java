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
     * Поиск позиции в коллекции.
     *
     * @param id id модели.
     * @return указатель.
     */
    private int indexOf(String id) {
        int result = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (id.equals(mem.get(index).getId())) {
                result = index;
                break;
            }
        }
        return result;
    }

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
     * @param id id модели.
     * @param model модель.
     * @return true or false.
     */
    @Override
    public boolean replace(String id, T model) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            mem.put(id, model);
        }
        return result;
    }

    /**
     * Удаляет модель по заданному id.
     *
     * @param id id модели.
     * @return true or false.
     */
    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            mem.remove(index, mem.get(id));
        }
        return rsl;
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
        int index = indexOf(id);
        return index != -1 ? mem.get(index) : null;
    }
}
