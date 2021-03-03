package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketInput;
import nodelibrary.editor.node.events.DataEvent;
// import nodelibrary.editor.node.events.SocketEvent;

public class NodeInput<T> extends NodeSection {

    private final SocketInput<T> socket;

    private Class<T> type;
    private T value;

    public DataControl<T> control;

    public NodeInput(Class<T> type, String labelText, DataControl<T> control, Node parent) {
        super(parent);
        this.type = type;

        Label label = new Label(labelText);
        grid.add(label, 0, 0);

        if (control != null) {
            this.control = control;

            grid.add(control, 0, 1);
        }

        socket = Socket.in(this, type);
        getChildren().add(socket);

        AnchorPane.setLeftAnchor(socket, -9.0);
        AnchorPane.setTopAnchor(socket, 11.0);
    }

    public void controlDisabled(boolean disabled) {
        if (control != null) {
            control.setDisable(disabled);
        }
    }

    public void updateSocket() {
        socket.updateConnections();
    }

    public void setValue(T value) {
        if (this.value != value) {
            this.value = value;
            this.fireEvent(new DataEvent(DataEvent.INPUT_UPDATE));
        }
    }

    public T getValue() {
        return value;
    }
}
