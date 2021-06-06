package nodelibrary.editor.node.components.control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import nodelibrary.editor.node.events.DataEvent;

public abstract class NumberInput<T extends Number> extends Group {
    
    public final TextField input = new TextField();
    private Rectangle screen = new Rectangle();

    protected final T min;
    protected final T max;
    protected final T initial;

    protected double xOffset;
    
    public NumberInput(T min, T max, T initial) {
        input.getStyleClass().add("number-input");

        this.min = min;
        this.max = max;
        this.initial = initial;

        input.setMinHeight(25);
        input.setMaxHeight(25);
        input.setAlignment(Pos.CENTER_LEFT);
        input.setStyle("-fx-border-color: black;" + 
            "-fx-border-width: 1 1 1 1;" + // top, right, bottom, left
            "-fx-background-radius: 10;"+
            "-fx-border-radius:10;"+
            "-fx-focus-color: transparent;"+
            "-fx-text-fill: black;"+
            "-fx-font-size: 1em"+
            "-fx-background-insets: -2, -0.3, 1, 2;"
        );
        input.setFont(new Font(100));

        screen.setFill(Color.TRANSPARENT);
        screen.heightProperty().bind(input.heightProperty());
        screen.widthProperty().bind(input.widthProperty());
        
        getChildren().addAll(input, screen);
        
        initHandlers();
    }

    public abstract T getValue();
    public abstract void setValue(T value);
    protected abstract void validate();
    protected abstract EventHandler<MouseEvent> dragHandler();

    protected Number clamp(Number value) {
        return Math.min(max.doubleValue(), Math.max(min.doubleValue(), value.doubleValue()));
    }

    private void initHandlers() {
        
        input.setOnKeyPressed(e -> { 
            if (e.getCode() == KeyCode.ENTER && input.getTextFormatter().getValue() == null) {
                input.setText(initial+"");
            } else if (e.getCode() == KeyCode.ENTER) {
                requestFocus();
                validate();
                this.fireEvent(new DataEvent(DataEvent.CONTROL_UPDATE));
            }

            e.consume();
        });

        setOnMouseClicked(e -> {
            if (e.getClickCount() % 2 == 0) {
                input.requestFocus();
            } else {
                requestFocus();
            }

            e.consume();
        });
        
        setOnMousePressed(e -> {
            xOffset = e.getSceneX();

            e.consume();
        });


        setOnMouseEntered(e -> {
            getScene().setCursor(Cursor.H_RESIZE);

            e.consume();
        });

        setOnMouseDragged(dragHandler());

        setOnMouseExited(e -> {
            getScene().setCursor(Cursor.DEFAULT);

            e.consume();
        });

        setOnMouseReleased(e -> {
            getScene().setCursor(Cursor.DEFAULT);
            
            e.consume();
        });
    }

    
    

}
