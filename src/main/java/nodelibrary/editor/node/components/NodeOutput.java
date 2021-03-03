package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketOutput;
import nodelibrary.editor.node.events.SocketEvent;

public class NodeOutput<T> extends NodeSection {

    public DataControl<T> control;
    public final SocketOutput<T> socket;

    private Class<T> type;
    private T value;

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

    }

    /**
     * Sets the value of the data contained within the NodeOutput
     * 
     * @param data
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

    public void updateSocket() {
        socket.updateConnections();
    }

    public void controlEnabled(boolean enabled) {
        if (control != null) {
            if (enabled) {
                if (!grid.getChildren().contains(control)) {
                    grid.add(control, 0, 1);
                }
            } else {
                if (grid.getChildren().contains(control)) {
                    grid.getChildren().remove(control);
                }
            }
        }
    }


}
