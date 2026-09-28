import bridge.AsciiRenderer;
import bridge.Circle;
import bridge.RasterRenderer;
import bridge.Renderer;
import bridge.Shape;
import bridge.Square;
import bridge.VectorRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String CIRCLE_ID = "CIR-1";
    private static final String SQUARE_ID = "SQR-1";
    private static final int CIRCLE_RADIUS = 2;
    private static final int SQUARE_SIDE = 3;
    private static final String CIRCLE_VECTOR = "VECTOR circle radius=2";
    private static final String CIRCLE_RASTER = "RASTER circle radius=2 bitmap=4x4";
    private static final String SQUARE_VECTOR = "VECTOR square side=3";
    private static final String SQUARE_RASTER = "RASTER square side=3 bitmap=3x3";
    private static final String CIRCLE_ASCII = "ASCII (o) circle radius=2";
    private static final String SQUARE_ASCII = "ASCII [#] square side=3";

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("--demo")) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }
        runDemo();
    }

    private static void runDemo() {
        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        Renderer ascii = new AsciiRenderer();
        List<Boolean> results = new ArrayList<>();
        results.add(checkCombination("T1", new Circle(CIRCLE_ID, CIRCLE_RADIUS, vector), vector, CIRCLE_VECTOR));
        results.add(checkCombination("T2", new Circle(CIRCLE_ID, CIRCLE_RADIUS, raster), raster, CIRCLE_RASTER));
        results.add(checkCombination("T3", new Square(SQUARE_ID, SQUARE_SIDE, vector), vector, SQUARE_VECTOR));
        results.add(checkCombination("T4", new Square(SQUARE_ID, SQUARE_SIDE, raster), raster, SQUARE_RASTER));
        results.add(checkRuntimeSwitch());
        results.add(checkCombination("T6", new Circle(CIRCLE_ID, CIRCLE_RADIUS, ascii), ascii, CIRCLE_ASCII));
        results.add(checkCombination("T7", new Square(SQUARE_ID, SQUARE_SIDE, ascii), ascii, SQUARE_ASCII));
        printSummary(results);
    }

    private static boolean checkCombination(String id, Shape shape, Renderer renderer, String expected) {
        String actual = shape.execute();
        boolean passed = actual.equals(expected);
        String line = id + " " + status(passed) + " | " + shape.getClass().getSimpleName() + " + "
                + renderer.getClass().getSimpleName() + " | result=" + actual;
        System.out.println(passed ? line : line + " | expected=" + expected);
        return passed;
    }

    private static boolean checkRuntimeSwitch() {
        Map<String, Shape> canvas = new HashMap<>();
        Shape original = new Circle(CIRCLE_ID, CIRCLE_RADIUS, new VectorRenderer());
        canvas.put(original.getId(), original);
        String idBefore = original.getId();
        int sizeBefore = original.getSize();
        String before = original.execute();
        original.setImplementation(new RasterRenderer());
        Shape afterSwitch = canvas.get(idBefore);
        String after = afterSwitch.execute();
        boolean sameObject = original == afterSwitch;
        boolean stateUnchanged = idBefore.equals(afterSwitch.getId()) && sizeBefore == afterSwitch.getSize();
        boolean resultsCorrect = before.equals(CIRCLE_VECTOR) && after.equals(CIRCLE_RASTER);
        boolean passed = sameObject && stateUnchanged && resultsCorrect;
        System.out.println("T5 " + status(passed) + " | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
        System.out.println(" before=" + before + " | after=" + after);
        if (!passed) {
            System.out.println(" expected before=" + CIRCLE_VECTOR + " | after=" + CIRCLE_RASTER);
        }
        return passed;
    }

    private static void printSummary(List<Boolean> results) {
        long passed = results.stream().filter(result -> result).count();
        System.out.println("SUMMARY: " + passed + "/" + results.size() + " PASS");
    }

    private static String status(boolean passed) {
        return passed ? "PASS" : "FAIL";
    }
}
