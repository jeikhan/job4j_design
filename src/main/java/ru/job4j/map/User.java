package ru.job4j.map;

import java.util.Calendar;

/**
 * Создать модель User и добавить три поля:
 * String name, int children, Calendar birthday.
 *
 * @author Evgenii Kapaev
 * @since 08.04.2022
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


}
