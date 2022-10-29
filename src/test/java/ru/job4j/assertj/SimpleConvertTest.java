package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тестирование методов toArray(), toList(), toSet(), toMap()
 * класса SimpleConvert.
 */
class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "third", "fourth", "fifth");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(array).hasSize(5)
                .contains("second", "fourth")
                .contains("third", Index.atIndex(2))
                .containsAnyOf("fourth", "sixth", "eighth")
                .containsOnly("first", "second", "third", "fourth", "fifth")
                .doesNotContain("ten", "twenty")
                .startsWith("first", "second")
                .endsWith("fifth")
                .containsSequence("fourth", "fifth")
                .doesNotContainSequence("first", "second", "three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("first", "second", "third", "fourth", "fifth", "first", "second");
        assertThat(array).hasSize(5)
                .contains("second", "fourth")
                .containsAnyOf("fourth", "zero", "ten")
                .containsOnly("first", "second", "third", "fourth", "fifth")
                .doesNotContain("one", "two", "three")
                .containsExactlyInAnyOrder("first", "second", "third", "fourth", "fifth");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> array = simpleConvert.toMap("first", "second", "third", "fourth", "fifth", "first", "second");
        assertThat(array).hasSize(5)
                .containsKeys("first", "second", "third", "fourth", "fifth")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKey("one")
                .doesNotContainValue(5)
                .containsEntry("first", 0)
                .containsEntry("fourth", 3);
    }
}