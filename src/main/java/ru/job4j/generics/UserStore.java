package ru.job4j.generics;

/**
 * Реализация хранилища для пользователя,
 * используя композицию объектов.
 *
 * @author Evgenii Kapaev
 * @since 16.11.2021
 */
public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    /**
     * Добавляет модель в хранилище.
     *
     * @param model модель.
     */
    @Override
    public void add(User model) {
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
    public boolean replace(String id, User model) {
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
    public User findById(String id) {
        return store.findById(id);
    }
}
