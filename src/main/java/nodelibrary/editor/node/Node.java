package nodelibrary.editor.node;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nodelibrary.editor.node.components.NodeLabel;
import nodelibrary.editor.node.components.NodeSection;

public abstract class Node extends Group {

    /**
     * xOffset and yOffset are used when calcuating the x and y position offset from
     * where a user clicks on the node.
     */
    private double xOffset;

    /**
     * xOffset and yOffset are used when calcuating the x and y position offset from
     * where a user clicks on the node.
     */
    private double yOffset;

    protected VBox container = new VBox();
    private Rectangle border = new Rectangle();

    public Node(double x, double y, String labelText) {
        initialize();

        NodeLabel label = new NodeLabel(labelText);
        container.getChildren().add(label);
        container.getStyleClass().add("node-container");

        border.widthProperty().bind(container.widthProperty());
        border.heightProperty().bind(container.heightProperty());
        border.setArcWidth(8);
        border.setArcHeight(8);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(2);
        

        relocate(x, y);

        getChildren().addAll(container, border);

        initHandlers();
    }

    /**
     * Initializes inputs and outputs of nodes
     */
    protected abstract void initialize();

    /**
     * The function required for a node to calculate its outputs
     */
    protected abstract void function();

    /**
     * A private method to set up the handlers for handling mouse events.
     */
    private void initHandlers() {

        // Initialises the xOffset and yOffset when the mouse is pressed
        setOnMousePressed(e -> {
            xOffset = e.getX();
            yOffset = e.getY();
        });
    }

    /**
     * Used to add a section to the node
     * 
     * @param section Thes ection to be added
     */
    protected void addSection(NodeSection section) {

    }



}







