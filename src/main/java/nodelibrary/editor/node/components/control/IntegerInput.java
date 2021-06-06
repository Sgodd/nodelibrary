package nodelibrary.editor.node.components.control;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import nodelibrary.editor.node.events.DataEvent;

public class IntegerInput extends NumberInput<Integer> {

    public IntegerInput(Integer min, Integer max, Integer initial) {
        super(min, max, initial);
        input.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0));
    }


    @Override 
    protected void validate() {
        Integer value = (Integer) input.getTextFormatter().getValue();
        value = clamp(value).intValue();
        input.setText(String.format("%d", value));
    }

    @Override 
    protected EventHandler<MouseEvent> dragHandler() {
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                getScene().setCursor(Cursor.H_RESIZE);
                Integer value = (Integer) input.getTextFormatter().getValue();
    
                value = clamp(value + (e.getSceneX() - xOffset)).intValue();

                xOffset = e.getSceneX();
                setValue(value);
                fireEvent(new DataEvent(DataEvent.CONTROL_UPDATE));

                e.consume();
            }
        };
    }

    @Override
    public Integer getValue() {
        return (Integer) input.getTextFormatter().getValue();
    }


    @Override
    public void setValue(Integer value) {
        input.setText(String.format("%d", value));
    }
}
