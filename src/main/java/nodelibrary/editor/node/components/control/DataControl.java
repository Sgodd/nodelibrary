package nodelibrary.editor.node.components.control;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import nodelibrary.editor.node.events.DataEvent;

public abstract class DataControl<T> extends VBox {

    public DataControl() {
        getStyleClass().add("value-control");
        setAlignment(Pos.CENTER);

        addEventHandler(DataEvent.CONTROL_UPDATE, e -> {
            
        });
        
    }

    public void disable() {}
    public void enable() {}

    public abstract T getValue();
    public abstract void setValue(T value);
}
