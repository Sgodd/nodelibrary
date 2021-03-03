package nodelibrary.editor.node.components.control;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public abstract class DataControl<T> extends VBox {

    public DataControl() {
        getStyleClass().add("value-control");
        setAlignment(Pos.CENTER);
    }

    public void disable() {}
    public void enable() {}

    public abstract T getValue();
}
