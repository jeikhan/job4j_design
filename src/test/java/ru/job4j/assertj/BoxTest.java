package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    public void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    public void isThisUnknownObject() {
        Box box = new Box(-1, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    public void whenFourVertices() {
        Box box = new Box(4, 4);
        int vert = box.getNumberOfVertices();
        assertThat(vert).isEqualTo(4);
    }

    @Test
    public void whenUnknownObject() {
        Box box = new Box(10, 10);
        int vert = box.getNumberOfVertices();
        assertThat(vert).isEqualTo(-1);
    }

    @Test
    public void whenExistObject() {
        Box box = new Box(4, 4);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void whenNotExistObject() {
        Box box = new Box(10, 4);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void getAreaSphere() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.6370614359173);
    }

    @Test
    public void getAreaNotExistObject() {
        Box box = new Box(-1, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0);
    }
}