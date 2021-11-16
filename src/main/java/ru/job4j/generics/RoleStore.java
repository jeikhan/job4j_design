package ru.job4j.generics;

/**
 * Реализация хранилища, используя
 * композицию объектов.
 *
 * @author Evgenii Kapaev
 * @since 16.11.2021
 */
public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    /**
     * Добавляет модель в хранилище.
     *
     * @param model модель.
     */
    @Override
    public void add(Role model) {
        store.add(model);
    }

    /**
     * Заменяет модель по заданному id.
     *
     * @param id    id модели.
     * @param model модель.
     * @return true or false.
     */
    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
    }

    /**
     * Удаляет модель по заданному id.
     *
     * @param id id модели.
     * @return true or false.
     */
    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    /**
     * Находит модель в хранилище по
     * заданному id.
     *
     * @param id id модели.
     * @return true or false.
     */
    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}
