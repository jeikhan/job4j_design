package ru.job4j.generics;

/**
 * Базовая модель объекта.
 *
 * @author Evgenii Kapaev
 * @since 16.11.2021
 */
public abstract class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
