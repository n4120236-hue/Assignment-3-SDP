package bridge;

public class Square extends Shape {
    public Square(String id, int side, Renderer renderer) {
        super(id, side, renderer);
    }

    @Override
    public String execute() {
        return renderer().drawSquare(getSize());
    }
}
