package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixItTest {
    @Test
    public void when1El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenFirstEmptyThenHasNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 3);
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }
}