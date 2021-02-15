package nodelibrary.editor.node.components.control;

import javafx.scene.Group;

public abstract class DataControl<T> extends Group {

    public DataControl() {
        getStyleClass().add("value-control");
    }

    public abstract T getData();
}
