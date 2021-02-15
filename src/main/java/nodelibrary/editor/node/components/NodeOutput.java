package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketOutput;

public class NodeOutput<T> extends NodeSection {

    private DataControl<T> dataControl;
    SocketOutput<T> socket;

    private Class<T> type;
    private T data;

    public NodeOutput(Class<T> type, String labelText, DataControl<T> dataControl) {
        super();
        this.type = type;

        Label label = new Label(labelText);
        this.dataControl = dataControl;

        grid.add(label, 0, 0);
        grid.add(dataControl, 0, 1);

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
    public void setValue(T data) {
        this.data = data;
    }

    /**
     * Returns the value of the data within the NodeOutput
     * 
     * @return The value of the data
     */
    public T getValue() {
        return data;
    }

    /**
     * Broadcasts the value of the data to all connected Inputs
     */
    public void broadcast() {
        
    }

    public void updateSocket() {
        socket.updateConnections();
    }

}
