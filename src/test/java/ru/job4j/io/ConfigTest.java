package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Evgeniy Kapaev");
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Evgeniy Kapaev");
    }

    @Test
    public void whenNotKeyOrValue() {
        String path = "./data/pair_with_no_key_or_value.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(IllegalAccessException.class, config::load);
    }
}