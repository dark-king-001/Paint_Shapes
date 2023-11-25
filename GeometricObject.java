import javafx.scene.paint.Color;

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
