package bridge;

public class Circle extends Shape {
    public Circle(String id, int radius, Renderer renderer) {
        super(id, radius, renderer);
    }

    @Override
    public String execute() {
        return renderer().drawCircle(getSize());
    }
}
