package ru.job4j.assertj;

/**
 * Задача. Написать по 2 теста на каждый из четырех
 * методов(whatsThis(), getNumberOfVertices(),
 * isExist(), getArea().
 *
 * @author Evgeniy Kapaev
 * @since 22.10.2022
 */
public class Box {
    private static final String UNKNOWN = "Unknown object";
    private int vertex;
    private final int edge;
    private String type = "";

    public Box(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        init();
    }

    /**
     * Определяет тип объекта и его характеристики.
     * Если количество вершин(vertex): 0 - "Sphere",
     * 4 - "Tetrahedron", 8 - "Cube". При ином количестве
     * вершин объект определяется как "Unknown object".
     */
    private void init() {
        type = switch (vertex) {
            case 0 -> "Sphere";
            case 4 -> "Tetrahedron";
            case 8 -> "Cube";
            default -> UNKNOWN;
        };
        if (UNKNOWN.equals(type)) {
            vertex = -1;
        }
        if (edge <= 0) {
            vertex = -1;
            type = UNKNOWN;
        }
    }

    /**
     * Возвращает тип объекта если он известен,
     * в противном случае объект определяется как
     * "Unknown object"
     *
     * @return "Sphere" или "Tetrahedron", или "Cube",
     * иначе "Unknown object".
     */
    public String whatsThis() {
        return this.type;
    }

    /**
     * Возвращает количество вершин объекта, если
     * он известен: "Sphere" - 0, "Tetrahedron" - 4,
     * "Cube" - 8, в противном случае -1.
     *
     * @return количество вершин, иначе -1.
     */
    public int getNumberOfVertices() {
        return this.vertex;
    }

    /**
     * Определяет существует ли объект.
     * Возвращает true если существует.
     *
     * @return true - существует, иначе false.
     */
    public boolean isExist() {
        return this.vertex != -1;
    }

    /**
     * Вычисляет площадь известного объекта.
     * Если объект неизвестен - возвращает 0.
     *
     * @return значение площади, иначе 0.
     */
    public double getArea() {
        double a = edge;
        return switch (vertex) {
            case 0 -> 4 * Math.PI * (a * a);
            case 4 -> Math.sqrt(3) * (a * a);
            case 8 -> 6 * (a * a);
            default -> 0;
        };
    }
}
