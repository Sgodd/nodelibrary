package nodelibrary.editor.node.components.sockets;

import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.events.SocketEvent;

public class SocketInput<T> extends Socket {

    private NodeInput<T> component;

    private SocketConnection<T> connection = null;

    public SocketInput(NodeInput<T> component, Class<T> type) {
        super(component);
        
        this.component = component;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public SocketConnection<T> createLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketOutput<T> socketOutput = (SocketOutput<T>) socket;
            SocketConnection<T> connection = new SocketConnection<>(socketOutput, this);

            if (this.connection != null) {
                SocketController.MAIN.removeConnection(this.connection);
            }

            this.connection = connection;
            SocketController.MAIN.addConnection(this.connection);

            socketOutput.addConnection(this.connection);

            this.fireEvent(new SocketEvent(SocketEvent.INPUT_LINKED, connection));

            return connection;
        } else {
            return null;
        }
    }

    public void setConnection(SocketConnection<T> connection) {
        removeConnection();
        this.connection = connection;
    }

    public void removeConnection(SocketConnection<T> connection) {
        if (this.connection == connection) {
            this.connection = null;
        }
    }

    public void removeConnection() {
        if (this.connection != null) {
            this.connection.destroy();
        }
    }

    @Override
    public void updateConnections() {
        if (connection != null) {
            connection.setEnd(getCenter());
        }
    }

    @Override
    public NodeInput<T> getSection() {
        return component;
    }
}

