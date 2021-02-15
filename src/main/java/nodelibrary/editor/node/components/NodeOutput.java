package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketOutput;
import nodelibrary.editor.node.events.SocketEvent;

public class NodeOutput<T> extends NodeSection {

    private DataControl<T> dataControl;
    SocketOutput<T> socket;

    private Class<T> type;
    private T value;

    public NodeOutput(Class<T> type, String labelText, DataControl<T> dataControl, Node parent) {
        super(parent);
        this.type = type;

        Label label = new Label(labelText);

        if (dataControl != null) {
            this.dataControl = dataControl;

            grid.add(label, 0, 0);
            grid.add(dataControl, 0, 1);
        }

        socket = Socket.out(this, type);
        getChildren().add(socket);

        AnchorPane.setRightAnchor(socket, -9.0);
        AnchorPane.setTopAnchor(socket, 11.0);

        addEventHandler(SocketEvent.OUTPUT_LINKED, e -> {
            e.connection.passValue();

            e.consume();
        });
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

}
