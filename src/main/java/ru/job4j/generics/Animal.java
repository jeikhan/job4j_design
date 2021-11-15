package ru.job4j.generics;

import java.util.Objects;

/**
 * Обобщения. Суперкласс иерархии
 * наследования Animal - Predator - Tiger
 *
 * @author Evgenii Kapaev
 * @since 15.11.2021
 */
public class Animal {
    private String name;
    private String sound;

    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Animal)) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) && Objects.equals(sound, animal.sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sound);
    }

    @Override
    public String toString() {
        return "Animal {"
                + "name = '" + name + '\''
                + ", sound = '" + sound + '\''
                + '}';
    }
}
