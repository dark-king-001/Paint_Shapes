import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Square class for use in assignment 8. Feel free to adapt to suit
 * your needs.
 *
 * @author Sam Scott
 */
public class Square extends GeometricObject {

    private double size;

    public Square(double x, double y, Color fillColor, double radius) {
        super(x, y, fillColor);
        this.size = radius;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillRect(getX() - size, getY() - size, size * 2, size * 2);
    }
}
