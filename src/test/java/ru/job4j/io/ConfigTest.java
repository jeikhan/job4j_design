package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Evgeniy Kapaev"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Evgeniy Kapaev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotKeyOrValue() {
        String path = "./data/pair_with_no_key_or_value.properties";
        Config config = new Config(path);
        config.load();
    }
}