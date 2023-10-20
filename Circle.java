import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Circle class for use in assignment 8. Feel free to adapt to suit
 * your needs
 *
 * @author Sam Scott
 */
public class Circle extends GeometricObject {

    private double radius;

    public Circle(double x, double y, Color fillColor, double radius) {
        super(x, y, fillColor);
        this.radius = radius;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }
}
