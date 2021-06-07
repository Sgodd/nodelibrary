package nodelibrary.editor.node.components.control;

import javafx.scene.Group;

public abstract class DataInput<T> extends Group {
    public abstract T getValue();
    public abstract void setValue(T value);
}
