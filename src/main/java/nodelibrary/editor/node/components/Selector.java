package nodelibrary.editor.node.components;

import javafx.scene.control.ComboBox;
import nodelibrary.editor.node.Node;

public class Selector<T> extends NodeSection {

    private ComboBox<T> dropdown = new ComboBox<>();
    private T value;

    public Selector(Node parent) {
        super(parent);

        grid.add(dropdown, 0, 0);

        dropdown.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            this.value = newVal;
            if (oldVal != null && oldVal != newVal) {
                parent.function();
            }
        });
    }    

    public void addItem(T item) {
        dropdown.getItems().add(item);
        dropdown.getSelectionModel().selectFirst();
    }


    public T getValue() {
        return value;
    }



}
