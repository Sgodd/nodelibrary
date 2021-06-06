package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketOutput;
import nodelibrary.editor.node.events.DataEvent;
import nodelibrary.editor.node.events.SocketEvent;

public class NodeOutput<T> extends NodeSection {

    // The DataControl object for the Output
    public DataControl<T> control;

    // The Class Type for the Input
    private final Class<T> type;

    // The value that is assosciated with the input.
    private T value;

    private final SocketOutput<T> socket;

    /**
     * Constructor for NodeOutput
     * 
     * @param type      The Class Type for the NodeInput
     * @param labelText The text to label the Section with
     * @param control   The DataControl object used to control the Value of the
     *                  input when there are no connections to the socket
     * @param parent    The parent Node which the section belongs to.
     */
    public NodeOutput(Class<T> type, String labelText, DataControl<T> control, Node parent) {
        super(parent);
        this.type = type;

        Label label = new Label(labelText);

        grid.add(label, 0, 0);

        if (control != null) {
            this.control = control;

            grid.add(control, 0, 1);
        }

        socket = Socket.out(this, type);
        getChildren().add(socket);

        AnchorPane.setRightAnchor(socket, -9.0);
        AnchorPane.setTopAnchor(socket, 11.0);

        addEventHandler(DataEvent.CONTROL_UPDATE, e -> {
            
            setValue(control.getValue());

            e.consume();
        });

        addEventHandler(SocketEvent.OUTPUT_LINKED, e -> {
            e.connection.passValue();

            e.consume();
        });
    }

    /**
     * A method used to update the positions of all connections to recenter them
     * with their sockets.
     */
    public void updateSocket() {
        socket.updateConnections();
    }

    /**
     * A method used to change whether the DataControl object is enabled or not. If
     * boolean value given is true, the control object if it exists is disabeld. If
     * disabled is false, the Control is active and can change the value of the
     * Input.
     * 
     * @param disabled Boolean value to set whether Control is disabled or not.
     */
    public void controlDisabled(boolean disabled) {
        if (control != null) {
            control.setDisable(disabled);
        }
    }

    /**
     * Sets the Value of the NodeOutput. If a change is detected the value is
     * updated and the new value is broadcasted to connected sockets.
     * 
     * @param value The new value to be assigned.
     */
    public void setValue(T value) {
        if (this.value != value) {
            this.value = value;

            socket.broadcast();
        }
    }

    /**
     * Returns the value of the data within the NodeOutput
     * 
     * @return The value of the data
     */
    public T getValue() {
        return value;
    }

}
