package nodelibrary.editor.node;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import nodelibrary.editor.node.components.NodeLabel;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.view.EditorCanvas;

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

    protected NodeOutput<?>[] outputs;
    // protected NodeInput<?>[] inputs;

    public Node(double x, double y, String labelText) {
        initialize();

        relocate(x, y);


        NodeLabel label = new NodeLabel(labelText);
        container.getChildren().add(label);
        container.getStyleClass().add("node-container");

        // setScaleX(EditorCanvas.GLOBAL_SCALE);
        // setScaleY(EditorCanvas.GLOBAL_SCALE);
       
        
        if (outputs.length > 0) {
            container.getChildren().addAll(outputs);
        }


        getChildren().addAll(container);

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
            System.out.println(getLayoutX() + ", " + getLayoutY());

        });

        setOnMouseDragged(e -> {

            Point2D pos = sceneToLocal(localToParent(e.getSceneX(), e.getSceneY()));
            relocate(pos.getX() - xOffset, pos.getY() - yOffset);
            System.out.println(getLayoutX() + ", " + getLayoutY());

        });
    }
}







