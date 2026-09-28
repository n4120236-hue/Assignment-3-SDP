package bridge;

import java.util.Objects;

public abstract class Shape {
    private final String id;
    private final int size;
    private Renderer renderer;

    protected Shape(String id, int size, Renderer renderer) {
        this.id = Objects.requireNonNull(id);
        this.size = size;
        this.renderer = Objects.requireNonNull(renderer);
    }

    public abstract String execute();

    public void setImplementation(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer);
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    protected Renderer renderer() {
        return renderer;
    }
}
