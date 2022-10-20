package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriggerTest {
    @Test
    public void test() {
        assertEquals(1, new Trigger().someLogic());
    }
}