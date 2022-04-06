package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}