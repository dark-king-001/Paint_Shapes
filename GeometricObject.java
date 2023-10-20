import javafx.scene.paint.Color;

/**
 * The parent class for Circle and Rectangle. Comments removed for display in
 * class. Constructors removed because they require a separate discussion.
 *
 * @author Sam Scott
 */
public abstract class GeometricObject implements Drawable {

    private double x, y;
    private Color fillColor;

    public GeometricObject(double x, double y, Color fillColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getFillColor() {
        return fillColor;
    }
}
