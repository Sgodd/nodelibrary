package nodelibrary.editor.node.components.control;

import javafx.scene.Group;

public abstract class ValueControl<T> extends Group {

    public ValueControl() {
        getStyleClass().add("value-control");
    }

    public abstract T getData();
}
