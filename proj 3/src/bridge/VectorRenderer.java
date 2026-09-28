package bridge;

public class VectorRenderer implements Renderer {
    @Override
    public String drawCircle(int radius) {
        return describe("circle", "radius", radius);
    }

    @Override
    public String drawSquare(int side) {
        return describe("square", "side", side);
    }

    private String describe(String shape, String dimension, int value) {
        return "VECTOR " + shape + " " + dimension + "=" + value;
    }
}
