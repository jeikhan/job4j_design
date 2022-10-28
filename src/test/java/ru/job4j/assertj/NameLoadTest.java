package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Тестирование методов getMap(), parse() класса NameLoad.
 */
class NameLoadTest {
    @Test
    public void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap).isInstanceOf(IllegalStateException.class).hasMessage("collection contains no data");
    }

    @Test
    public void whenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse).isInstanceOf(IllegalArgumentException.class).hasMessage("Names array is empty");
    }

    @Test
    public void whenNameNotContainSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] args = {"name"};
        assertThatThrownBy(() -> nameLoad.parse(args)).isInstanceOf(IllegalArgumentException.class).hasMessage("this name: name does not contain the symbol \"=\"");
    }

    @Test
    public void whenNameStartWithSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] args = {"=value", "noName"};
        assertThatThrownBy(() -> nameLoad.parse(args)).isInstanceOf(IllegalArgumentException.class).hasMessage("this name: =value does not contain a key");
    }

    @Test
    public void whenNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] args = {"name=", "noName"};
        assertThatThrownBy(() -> nameLoad.parse(args)).isInstanceOf(IllegalArgumentException.class).hasMessage("this name: name= does not contain a value");
    }
}