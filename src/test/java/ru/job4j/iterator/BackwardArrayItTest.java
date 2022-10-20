package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BackwardArrayItTest {
    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertEquals(3, it.next());
        assertEquals(2, it.next());
        assertEquals(1, it.next());
    }

    @Test
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{}
        );
        Exception exception = assertThrows(NoSuchElementException.class, it::next);
    }
}