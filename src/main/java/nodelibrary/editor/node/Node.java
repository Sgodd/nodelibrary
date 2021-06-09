package nodelibrary.editor.node;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import nodelibrary.editor.node.components.NodeLabel;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.NodeSection;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.events.DataEvent;
import nodelibrary.editor.node.events.NodeEvent;
import nodelibrary.editor.node.events.SocketEvent;
import nodelibrary.editor.node.components.NodeInput;

public abstract class Node extends Group {

    /**
     * The VBox to contain vertically spaced components of the node
     */
    protected VBox container = new VBox();


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

    // List of NodeOutputs<?>
    private ArrayList<NodeOutput<?>> outputs = new ArrayList<>();
    private ArrayList<NodeInput<?>> inputs = new ArrayList<>();

    public Node(double x, double y, String labelText) {
        NodeLabel label = new NodeLabel(labelText, this);
        addSection(label);
        
        initialize();


        container.getStyleClass().add("node-container");
        container.getStyleClass().add("node-section");

        getChildren().addAll(container);

        initHandlers();

        relocate(x - 50, y - 50);
    }  

    /**
     * Method to create an input for a node with a specified type and label given
     * and adds it to the list of inputs.
     * 
     * @param <T>   The type specification for the class of the NodeInput
     * @param type  A parameter for the Class of T
     * @param label The label for the NodeInput
     * 
     * @return A newly constructed NodeInput<T>
     */
    public <T> NodeInput<T> input(Class<T> type, String label) {
        NodeInput<T> input = new NodeInput<T>(type, label, null, this);
        inputs.add(input);
        addSection(input);

        return input;
    }

    /**
     * Method to create an input for a node with a specified type and label and a
     * given DataControl component and adds to a list of inputs for the node.
     * 
     * @param <T>     The type specification for the class of the NodeInput
     * @param type    A parameter for the Class of T
     * @param label   The label for the NodeInput
     * @param control The DataControl component assosciated with the NodeInput
     * 
     * @return A newly constructed NodeInput<T>
     */
    public <T> NodeInput<T> input(Class<T> type, String label, DataControl<T> control) {
        NodeInput<T> input = new NodeInput<T>(type, label, control, this);
        inputs.add(input);
        addSection(input);

        return input;
    }

    /**
     * Method to create an output for a node with a specified type and label given
     * and adds it to a list of Outputs.
     * 
     * @param <T>   The type specification for the class of the NodeOutput
     * @param type  A parameter for the Class of T
     * @param label The label for the NodeInput
     * 
     * @return A newly constructed NodeOutput<T>
     */
    public <T> NodeOutput<T> output(Class<T> type, String label) {
        NodeOutput<T> output = new NodeOutput<T>(type, label, null, this);
        outputs.add(output);
        addSection(output);

        return output;
    }

    /**
     * Method to create an output for a node with a specified type and label given
     * and a given DataControl component and adds to a list of outputs for the node.
     * 
     * @param <T>     The type specification for the class of the NodeOutput
     * @param type    A parameter for the Class of T
     * @param label   The label for the NodeInput
     * @param control The DataControl component assosciated with the NodeOutput
     * 
     * @return A newly constructed NodeOutput<T>
     */
    public <T> NodeOutput<T> output(Class<T> type, String label, DataControl<T> control) {
        NodeOutput<T> output = new NodeOutput<T>(type, label, control, this);
        outputs.add(output);
        addSection(output);

        return output;
    }

    public void addSection(NodeSection section) {
        container.getChildren().add(section);
    }

    /**
     * 
     */
    public void updateSockets() {
        for (NodeOutput<?> output : outputs) {
            output.updateSocket();
        }

        for (NodeInput<?> input : inputs) {
            input.updateSocket();
        }
    }

    /**
     * Initializes inputs and outputs of nodes
     */
    protected abstract void initialize();

    /**
     * The function required for a node to calculate its outputs
     */
    public abstract void function();

    /**
     * A private method to set up the handlers for handling mouse events.
     */
    private void initHandlers() {

        // Initialises the xOffset and yOffset when the mouse is pressed
        setOnMousePressed(e -> {
            xOffset = e.getX();
            yOffset = e.getY();

            toFront();
            requestFocus();

            updateSockets();
        });

        // On mouse drag on a node, relocate the position of the node relative to the x
        // and y offsets
        setOnMouseDragged(e -> {
            // xOffset - 7.0 to account for socket protrusion
            relocate(e.getSceneX() - xOffset - 7.0, e.getSceneY() - yOffset);
            updateSockets();
        });

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE) {
                fireEvent(new NodeEvent(NodeEvent.DELETED, this));
                destroy();
            }
            e.consume();
        });

        // Handles when an input event is triggered
        addEventHandler(DataEvent.INPUT_UPDATE, e -> {
            try {
                function();
            } catch (NullPointerException err) {

            }
            e.consume();
        });

        // Handles when a socket event is triggered
        addEventHandler(SocketEvent.SOCKET_EVENT, e -> {
            updateSockets();
        });
    }

    private void destroy() {
        for (NodeOutput<?> output : outputs) {
            output.destroy();
        }

        for (NodeInput<?> input : inputs) {
            input.destroy();
        }
    }
}
