package bridge;

public class RasterRenderer implements Renderer {
    @Override
    public String drawCircle(int radius) {
        return describe("circle", "radius", radius, 2 * radius);
    }

    @Override
    public String drawSquare(int side) {
        return describe("square", "side", side, side);
    }

    private String describe(String shape, String dimension, int value, int pixels) {
        return "RASTER " + shape + " " + dimension + "=" + value + " bitmap=" + pixels + "x" + pixels;
    }
}
