package nodelibrary.editor.node.components.control;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DoubleStringConverter;

public class DoubleInput extends NumberInput<Double> {

    public DoubleInput(Double min, Double max, Double initial) {
        super(min, max, initial);

        input.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0));
    }

    @Override
    protected Double getValue() {
        return (Double) input.getTextFormatter().getValue();
    }

    @Override 
    protected void validate() {
        Double value = (Double) input.getTextFormatter().getValue();
        value = clamp(value).doubleValue();
        input.setText(String.format("%.10f", value));
    }

    @Override 
    protected EventHandler<MouseEvent> dragHandler() {
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                getScene().setCursor(Cursor.H_RESIZE);
                Double value = (Double) input.getTextFormatter().getValue();
    
                if (e.isShiftDown()) {
                    value += value + 0.1 * (e.getSceneX() - xOffset);
        
                } else {
                    value += 1 * (e.getSceneX() - xOffset);
                }
                    
                xOffset = e.getSceneX();
                input.setText(String.format("%.10f", value));

                validate();
    
                e.consume();
    
            }
        };
    }
}
