package nodelibrary.editor.node.components.control;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.converter.DoubleStringConverter;

public class DoubleControlOld extends DataControl<Double> {


    private Rectangle base = new Rectangle();
    private TextField textField = new TextField();
    private double xOffset = 0;

    public DoubleControlOld() {
        // relocate(100,10);

        base.setWidth(60);
        base.setHeight(25);
        base.setFill(Color.TRANSPARENT);

        textField.setMaxWidth(base.getWidth()-1);
        textField.setMinWidth(base.getWidth()-1);
        textField.setMaxHeight(base.getHeight()-1);
        textField.setMinHeight(base.getHeight()-1);
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0));
        textField.setStyle("-fx-border-color: black;" + 
            "-fx-border-width: 1 1 1 1;" + // top, right, bottom, left
            "-fx-background-color: rgba(150,150,150,1);"+
            "-fx-background-radius: 10;"+
            "-fx-border-radius:109;"+
            "-fx-font-size:1em;"
        );

        initHandlers();
    }

    private void initHandlers() {
        textField.setOnKeyPressed(e -> { 
            if (e.getCode() == KeyCode.ENTER && textField.getTextFormatter().getValue() == null) {
                textField.setText(0+"");

                e.consume();
            }
        });

        setOnMouseClicked(e -> {
            if (e.getClickCount() % 2 == 0) {
                textField.requestFocus();
            } else {
                requestFocus();
            }
            e.consume();
        });


        setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            e.consume();

        });

        setOnMouseDragged(e -> {
            getScene().setCursor(Cursor.H_RESIZE);
            double value = (double) textField.getTextFormatter().getValue();

            if (e.isShiftDown()) {
                value += 0.1 * (e.getSceneX() - xOffset);
                // value = Math.round(value * 1000)/1000;
 
            } else {
                value += 1 * (e.getSceneX() - xOffset);
            }
               
            xOffset = e.getSceneX();
            setValue(value);
            e.consume();

        });

        setOnMouseReleased(e -> {
            getScene().setCursor(Cursor.DEFAULT);
            e.consume();
        });

        disableProperty().addListener(e -> {
            System.out.println("Changed");
            setValue(0.0);
        });
    }
    
    private void setValue(Double value) {
        textField.setText(String.format("%.10f", value));
    }

    public Double getValue() {
        return (Double) textField.getTextFormatter().getValue();
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void enable() {
        // TODO Auto-generated method stub

    }
}
