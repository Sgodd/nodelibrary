package nodelibrary.editor.node;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import nodelibrary.editor.node.components.NodeLabel;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.NodeInput;
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

    /**
     * The VBox to contain vertically spaced components of the node
     */
    protected VBox container = new VBox();

    private ArrayList<NodeOutput<?>> outputs = new ArrayList<>();
    private ArrayList<NodeInput<?>> inputs = new ArrayList<>();
    // private NodeOutput<?>[] outputs = new ArNodeOutput<?>[0];
    // private NodeInput<?>[]  inputs  = new NodeInput<?>[0];

    public Node(double x, double y, String labelText) {
        initialize();

        relocate(x, y);


        NodeLabel label = new NodeLabel(labelText);
        container.getChildren().add(label);
        container.getStyleClass().add("node-container");
        
        // setScaleX(EditorCanvas.GLOBAL_SCALE);
        // setScaleY(EditorCanvas.GLOBAL_SCALE);
       
        if (!outputs.isEmpty()) {
            container.getChildren().addAll(outputs);
        }

        if (!inputs.isEmpty()) {
            container.getChildren().addAll(inputs);
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
        });

        // On mouse drag on a node, relocate the position of the node relative to the x and y offsets
        setOnMouseDragged(e -> {
            // xOffset - 7.0 to account for socket protrusion
            relocate(e.getSceneX() - xOffset - 7.0, e.getSceneY() - yOffset);
        });
    }

    public <T> NodeInput<T> input(Class<T> type, String label) {
        NodeInput<T> input = new NodeInput<T>(type, label);
        inputs.add(input);

        return input;
    }

    public <T> NodeOutput<T> output(Class<T> type, String label, DataControl<T> control) {
        NodeOutput<T> output = new NodeOutput<T>(type, label, control);
        outputs.add(output);

        return output;
    }
}







