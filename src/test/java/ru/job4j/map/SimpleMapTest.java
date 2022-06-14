package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenPutSuccess() {
        Map<String, User> passports = new SimpleMap<>();
        User ivan = new User(
                "Иванов Иван Иванович",
                1,
                new GregorianCalendar(1992, Calendar.FEBRUARY, 10)
        );
        assertTrue(passports.put("2404 113029", ivan));
    }

    @Test
    public void whenPutFail() {
        Map<String, User> passports = new SimpleMap<>();
        User ivan = new User(
                "Иванов Иван Иванович",
                1,
                new GregorianCalendar(1992, Calendar.FEBRUARY, 10)
        );
        User petr = new User(
                "Петров Петр Петрович",
                2,
                new GregorianCalendar(1990, Calendar.OCTOBER, 1)
        );
        assertTrue(passports.put("2404 113029", ivan));
        assertFalse(passports.put("2404 113029", petr));
    }

    @Test
    public void whenGetSuccess() {
        Map<String, User> passports = new SimpleMap<>();
        User ivan = new User(
                "Иванов Иван Иванович",
                1,
                new GregorianCalendar(1992, Calendar.FEBRUARY, 10)
        );
        passports.put("2404 113029", ivan);
        assertEquals(ivan, passports.get("2404 113029"));
    }

    @Test
    public void whenGetFail() {
        Map<String, User> passports = new SimpleMap<>();
        assertNull(passports.get("2404 113029"));
    }

    @Test
    public void whenRemoveSuccess() {
        Map<String, User> passports = new SimpleMap<>();
        User ivan = new User(
                "Иванов Иван Иванович",
                1,
                new GregorianCalendar(1992, Calendar.FEBRUARY, 10)
        );
        User petr = new User(
                "Петров Петр Петрович",
                2,
                new GregorianCalendar(1990, Calendar.OCTOBER, 1)
        );
        passports.put("2404 113029", ivan);
        passports.put("2404 113530", petr);
        assertTrue(passports.remove("2404 113530"));
    }

    @Test
    public void whenRemoveFail() {
        Map<String, User> passports = new SimpleMap<>();
        User ivan = new User(
                "Иванов Иван Иванович",
                1,
                new GregorianCalendar(1992, Calendar.FEBRUARY, 10)
        );
        User petr = new User(
                "Петров Петр Петрович",
                2,
                new GregorianCalendar(1990, Calendar.OCTOBER, 1)
        );
        passports.put("2404 113029", ivan);
        passports.put("2404 113530", petr);
        assertFalse(passports.remove("2404 113529"));
    }
}