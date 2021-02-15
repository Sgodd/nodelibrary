package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import nodelibrary.editor.node.Node;

public class NodeLabel extends NodeSection {
    
    public NodeLabel(String labelText, Node parent) {
        super(parent);

        Label label = new Label(labelText);
        grid.add(label, 0, 0);
    }
}
