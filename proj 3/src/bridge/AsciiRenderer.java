package bridge;

public class AsciiRenderer implements Renderer {
    @Override
    public String drawCircle(int radius) {
        return describe("(o)", "circle", "radius", radius);
    }

    @Override
    public String drawSquare(int side) {
        return describe("[#]", "square", "side", side);
    }

    private String describe(String symbol, String shape, String dimension, int value) {
        return "ASCII " + symbol + " " + shape + " " + dimension + "=" + value;
    }
}
