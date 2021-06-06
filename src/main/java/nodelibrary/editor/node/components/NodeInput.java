package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketInput;
import nodelibrary.editor.node.events.DataEvent;
// import nodelibrary.editor.node.events.SocketEvent;
import nodelibrary.editor.node.events.SocketEvent;

public class NodeInput<T> extends NodeSection {

    // The DataControl object for the Input
    public DataControl<T> control;

    // The value that is assosciated with the input.
    private T value;
    
    // The Class Type for the Input
    private final Class<T> type;

    private final SocketInput<T> socket;

    /**
     * Constructor for NodeInput
     * 
     * @param type      The Class Type for the NodeInput
     * @param labelText The text to label the Section with
     * @param control   The DataControl object used to control the Value of the
     *                  input when there are no connections to the socket
     * @param parent    The parent Node which the section belongs to.
     */
    public NodeInput(Class<T> type, String labelText, DataControl<T> control, Node parent) {
        super(parent);
        this.type = type;

        Label label = new Label(labelText);
        grid.add(label, 0, 0);

        // If a control is given then we add it to the view
        if (control != null) {
            this.control = control;

            grid.add(control, 0, 1);
        }

        // Initialise a new Socket Input with a specified type.
        socket = Socket.in(this, type);
        getChildren().add(socket);

        AnchorPane.setLeftAnchor(socket, -9.0);
        AnchorPane.setTopAnchor(socket, 11.0);

        addEventHandler(DataEvent.CONTROL_UPDATE, e -> {
            setValue(control.getValue());

            e.consume();
        });

        addEventHandler(SocketEvent.INPUT_LINKED, e -> {
            e.connection.passValue();

            e.consume();
        });
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
     * A method used to update the positions of all connections to recenter them
     * with their sockets.
     */
    public void updateSocket() {
        socket.updateConnections();
    }

    /**
     * Method used to set the value of the NodeInput. If a value is changed then a
     * new DataEvent.INPUT_UPDATe is fired.  If there is no change, nothing happens.
     * 
     * @param value The new value of the Input.
     */
    public void setValue(T value) {
        if (this.value != value) {
            this.value = value;

            if (control != null) {
                control.setValue(value);
            }

            this.fireEvent(new DataEvent(DataEvent.INPUT_UPDATE));
        }
    }

    /**
     * Returns the current value of the NodeInput
     * 
     * @return The value of the NodeInput.
     */
    public T getValue() {
        return value;
    }
}
