package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotKey() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-=512", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("В аргументе отсутствует ключ");
    }

    @Test
    void whenNotValue() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("В аргументе отсутствует значение");
    }

    @Test
    void whenWrongValue() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx:512", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Неверно указан аргумент значения");
    }

    @Test
    void whenWrongKey() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"Xmx=512", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Неверно указан аргумент ключа");
    }
}