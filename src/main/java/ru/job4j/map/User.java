package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Создать модель User и добавить три поля:
 * String name, int children, Calendar birthday.
 *
 * @author Evgenii Kapaev
 * @since 08.04.2022
 */
public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", children=" + children +
                ", birthday=" + birthday.getWeekYear() +
                '}';
    }

    public static void main(String[] args) {
        User user1 = new User("Иванов Иван", 2, new GregorianCalendar(1960, Calendar.APRIL, 11));
        User user2 = new User("Иванов Иван", 2, new GregorianCalendar(1960, Calendar.APRIL, 11));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.put(user2, new Object());
        for (User element : map.keySet()) {
            System.out.println(element);
        }
    }
}
