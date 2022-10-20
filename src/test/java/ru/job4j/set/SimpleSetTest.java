package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleSetTest {
    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenNotContains() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertFalse(set.contains(2));
    }

    @Test
    public void whenAddMultiple() {
        Set<Integer> set = new SimpleSet<>();
        set.add(2);
        set.add(2);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 1);
    }
}