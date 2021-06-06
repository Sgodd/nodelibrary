package nodelibrary.editor.node.components.control;

import javafx.scene.layout.HBox;

public abstract class NumberControl<T extends Number> extends DataControl<T> {

    protected NumberInput<T> input;
    protected HBox hbox;

    public NumberControl(NumberInput<T> input) {
        this.input = input;

        hbox = new HBox();

        getChildren().add(hbox);
        hbox.getChildren().add(this.input);
    }

    public void setValue(T value) {
        input.setValue(value);
    }
}