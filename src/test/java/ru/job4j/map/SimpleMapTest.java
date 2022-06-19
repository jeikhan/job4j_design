package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenPutSuccess() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
    }

    @Test
    public void whenPutFail() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertFalse(map.put(1, "two"));
    }

    @Test
    public void whenGetSuccess() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertEquals("one", map.get(1));
    }

    @Test
    public void whenGetFail() {
        Map<Integer, String> map = new SimpleMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void whenRemoveSuccess() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenRemoveFail() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertFalse(map.remove(3));
    }

    @Test
    public void whenHasNextAndNotNext() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenHaveNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, null);
        map.put(3, "three");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", map.get(1));
        assertTrue(iterator.hasNext());
        assertEquals("three", map.get(3));
    }
}