package ru.job4j.generics;

/**
 * Обобщения. Подкласс иерархии
 * наследования Animal - Predator - Tiger
 *
 * @author Evgenii Kapaev
 * @since 15.11.2021
 */
public class Tiger extends Predator {
    public Tiger(String name, String sound) {
        super(name, sound);
    }
}
