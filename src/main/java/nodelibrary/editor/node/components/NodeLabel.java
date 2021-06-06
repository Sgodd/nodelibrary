package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import nodelibrary.editor.node.Node;

/**
 * NodeLabel.java
 * 
 * A simple class to Label the node.
 * 
 * @author Scott Barr
 */
public class NodeLabel extends NodeSection {
    
    /**
     * Constructs a new NodeSection with a label.
     * 
     * @param labelText The text for the label of the node
     * @param parent The Node object which the NodeSection belongs to.
     */
    public NodeLabel(String labelText, Node parent) {
        super(parent);

        Label label = new Label(labelText);
        grid.add(label, 0, 0);
    }
}
