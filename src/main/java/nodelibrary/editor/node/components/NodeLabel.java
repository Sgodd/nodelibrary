package nodelibrary.editor.node.components;

import javafx.scene.control.Label;

public class NodeLabel extends NodeSection {
    
    public NodeLabel(String labelText) {
        super();

        Label label = new Label(labelText);
        grid.add(label, 0, 0);
    }
}
