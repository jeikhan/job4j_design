package ru.job4j.map;

import java.util.*;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getWeekYear()
                + '}';
    }

    public static void main(String[] args) {
        User user1 = new User("Иванов Иван", 2, new GregorianCalendar(1960, Calendar.APRIL, 11));
        User user2 = new User("Иванов Иван", 2, new GregorianCalendar(1960, Calendar.APRIL, 11));
        Map<User, Object> map = new HashMap<>();
        System.out.println("Хеш-коды объектов:");
        System.out.println("user1: " + user1.hashCode());
        System.out.println("user2: " + user2.hashCode());
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println("Значения объектов:");
        System.out.println("user1: " + map.get(user1));
        System.out.println("user2: " + map.get(user2));
        System.out.println("Вывод:");
        for (User element : map.keySet()) {
            System.out.print(element + " " + "hashCode = ");
            System.out.println(element.hashCode());
        }
    }
}
