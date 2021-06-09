package imgproc.nodes.components;

import imgproc.functions.ImageFunction;
import javafx.scene.control.ComboBox;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeSection;

public class FunctionSelector extends NodeSection {

    private ComboBox<ImageFunction> dropdown = new ComboBox<>();

    public FunctionSelector(Node parent) {
        super(parent);

        grid.add(dropdown, 0, 1);
    }

    public ImageFunction getSelected() {
        return dropdown.getSelectionModel().getSelectedItem();
    }
    
    public void addFunction(ImageFunction f) {
        dropdown.getItems().add(f);
    }
}
