package nodelibrary.editor.node.components;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import nodelibrary.editor.node.Node;

/**
 * NodeSection.Java
 * 
 * A class to encompass a section of a Node.
 * 
 * @author Scott Barr
 */
public abstract class NodeSection extends AnchorPane {

    protected GridPane grid = new GridPane();
    private final Node parent;

    /**
     * Constructor for NodeSection.
     * 
     * @param parent The Node object which the section will belong to.
     */
    public NodeSection(Node parent) {
        this.parent = parent;

        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);

        setMinWidth(50);

        grid.setPadding(new Insets(5, 10, 5, 10));
        grid.setMinWidth(100);
        grid.setVgap(4);
        grid.setHgap(4);

        getStyleClass().add("node-section");
        getChildren().add(grid);
    }

    /**
     * Gets the parent Node object to which the section belongs to.
     * 
     * @return A Node Object
     */
    public Node getNode() {
        return parent;
    }

}
