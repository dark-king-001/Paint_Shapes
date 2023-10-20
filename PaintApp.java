import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
// import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

/**
 * Expand this FXGUITemplate into your own Paint app. Don't forget to
 * comment according to course commenting standards.
 * 
 * @author YOUR NAME
 */
public class PaintApp extends Application {

    // TODO: Instance Variables for View Components and Model
    private final static int MAXROWS = 800;
    private final static int MAXCOLS = 1200;
    private GeometricObject object;
    private ArrayList<GeometricObject> objects;
    private GraphicsContext gc;

    private Button circleButton;
    private Button squareButton;
    private Button clearButton;
    private Button drawButton;
    private Button undrawButton;
    public String selectedShape = "Circle"; 

    private Label fillColorLabel;
    private ColorPicker fillColorPicker; 

    private Label instructionLabel;

    private Label lengthLabel; // New instance variable for length label
    private TextField lengthTextField; // New instance variable for length

    private Label locationLabel; // New instance variable for y location        
    private TextField locationXTextField; // New instance variable for x location
    private TextField locationYTextField; // New instance variable for y location

    // TODO: Private Event Handlers and Helper Methods
    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    public void pressHandler(MouseEvent me) {   
        try {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (me.getY() < MAXROWS - 100) {
                    double length = Double.parseDouble(lengthTextField.getText());
                    Color fillColor = fillColorPicker.getValue();

                    locationXTextField.setText(String.format("%.2f", me.getX())); // Update x location
                    locationYTextField.setText(String.format("%.2f", me.getY())); // Update y location

                    switch (selectedShape) {
                        case "Circle":
                            object = new Circle(me.getX(), me.getY(),fillColor,length);
                            break;
                        case "Square":
                            object = new Square(me.getX(), me.getY(),fillColor,length);
                            break;
                        default:
                            return;
                    }
                    objects.add(object);
                    updateFrame();
                }
            // } else if (me.getButton() == MouseButton.SECONDARY) {
            //     if (me.getY() < MAXROWS - 200) {
            //         for (Shape s : shapes) {
            //             if (s.contains(me.getX(), me.getY())) {
            //                 if (rightSelectedShape != null)
            //                     rightSelectedShape.rightSelectionToggleOff();
            //                 rightSelectedShape = s;
            //                 rightSelectedShape.rightSelectionToggleOn();                        
            //                 updateFrame();
            //                 break;
            //             }
            //         }
            //     }
            // }
            }
        } catch (NumberFormatException ex) {
            // Handle the exception by displaying an error message
            new Alert(Alert.AlertType.WARNING, "Invalid Line Width").showAndWait();
        }
        
    }
    public void dragHandler(MouseEvent me) {
        if (me.isControlDown() && me.getButton() == MouseButton.PRIMARY) {
            if (me.getY() < MAXROWS - 100) {
                double length = Double.parseDouble(lengthTextField.getText());
                Color fillColor = fillColorPicker.getValue();

                locationXTextField.setText(String.format("%.2f", me.getX())); // Update x location
                locationYTextField.setText(String.format("%.2f", me.getY())); // Update y location

                switch (selectedShape) {
                    case "Circle":
                        object = new Circle(me.getX(), me.getY(), fillColor, length);
                        break;
                    case "Square":
                        object = new Square(me.getX(), me.getY(), fillColor, length);
                        break;
                    default:
                        return;
                }
                objects.add(object);
                updateFrame();
            }
        }
    }
    private void drawButtonHandler(ActionEvent event){
        double length = Double.parseDouble(lengthTextField.getText());
        Color fillColor = fillColorPicker.getValue();
        double posx = Double.parseDouble(locationXTextField.getText());
        double posy = Double.parseDouble(locationYTextField.getText());
        switch (selectedShape) {
            case "Circle":
                object = new Circle(posx, posy, fillColor, length);
                break;
            case "Square":
                object = new Square(posx, posy, fillColor, length);
                break;
            default:
                return;
        }
        objects.add(object);
        updateFrame();
    }
    
    private void circleButtonHandler(ActionEvent event) {
        selectedShape = "Circle";
        lengthLabel.setText("Radius:");
    }
    private void squareButtonHandler(ActionEvent event) {
        selectedShape = "Square";
        lengthLabel.setText("length:");
    }
    private void clearButtonHandler(ActionEvent event) {
        objects.clear();
        updateFrame();
    }
    private void undrawButtonHandler(ActionEvent event) {
        if (!objects.isEmpty()) {
            objects.remove(objects.size() - 1);
            updateFrame();
        }
    }
    public void updateFrame() {

        undrawButton.setDisable(objects.isEmpty());

        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,MAXCOLS, MAXROWS-100);

        for( GeometricObject s : objects)
            s.draw(gc);

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,MAXROWS - 100, MAXCOLS,200);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, MAXCOLS, MAXROWS); // set the size here
        stage.setTitle("PaintApp"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        objects = new ArrayList<GeometricObject>();
        // 2. Create the GUI components
        Canvas c = new Canvas(MAXCOLS, MAXROWS);

        circleButton = new Button("Circle");
        squareButton = new Button("Square");
        clearButton = new Button("Clear Canvas");
        drawButton = new Button("Draw");
        undrawButton = new Button("unDraw");

        fillColorLabel = new Label("Fill Color:");
        fillColorPicker = new ColorPicker(Color.BLUE); // Initial value for black color

        lengthLabel = new Label("Radius:");
        lengthTextField = new TextField("20.0"); // Initial value for width

        locationLabel = new Label("Location:");
        locationXTextField = new TextField("0.0");
        locationYTextField = new TextField("0.0");

        instructionLabel = new Label("INSTRUCTIONS\n"
                        +"Left-click to draw shapes.\n"
                        + "Hold Ctrl to drag draw shapes"
                        + "Use Draw to paint shape.on the location\n"
                        + "Use UnDraw to remove the last shape\n"
                        + "left click mouse on canvas to store the location"
        );

        // 3. Add components to the root
        root.getChildren().addAll(
            c, 
            circleButton, 
            squareButton, 
            clearButton,
            drawButton, 
            undrawButton,
            lengthLabel,
            lengthTextField,
            fillColorLabel, 
            fillColorPicker,
            locationLabel,
            locationXTextField,
            locationYTextField,
            instructionLabel
        );

        circleButton.setOnAction(this::circleButtonHandler);
        squareButton.setOnAction(this::squareButtonHandler);
        
        clearButton.setOnAction(this::clearButtonHandler);
        drawButton.setOnAction(this::drawButtonHandler);
        undrawButton.setOnAction(this::undrawButtonHandler);
        // 4. Configure the components (colors, fonts, size, location)
        gc = c.getGraphicsContext2D();
        updateFrame();
        circleButton.relocate(20, MAXROWS - 90);
        squareButton.relocate(80, MAXROWS - 90);
        clearButton.relocate(MAXCOLS - 120, MAXROWS - 40);
        drawButton.relocate(MAXCOLS - 140, MAXROWS -90);
        undrawButton.relocate(MAXCOLS - 80, MAXROWS - 90);

        lengthLabel.relocate(200, MAXROWS - 60); // Adjust the y-coordinate as needed
        lengthTextField.relocate(200, MAXROWS - 40); // Adjust the y-coordinate as needed

        fillColorLabel.relocate(20, MAXROWS - 60);
        fillColorPicker.relocate(20, MAXROWS - 40);

        locationLabel.relocate(MAXCOLS - 350, MAXROWS - 90);
        locationXTextField.relocate(MAXCOLS - 350, MAXROWS - 70); // Adjust the location as needed
        locationYTextField.relocate(MAXCOLS - 350, MAXROWS - 40); // Adjust the location as needed

        instructionLabel.relocate(MAXCOLS -350, MAXROWS - 180);

        // 5. Add Event Handlers and do final setup
        c.addEventHandler(MouseEvent.MOUSE_PRESSED,this::pressHandler);
        c.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
