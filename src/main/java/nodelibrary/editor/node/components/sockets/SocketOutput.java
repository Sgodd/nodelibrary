package nodelibrary.editor.node.components.sockets;

import java.util.ArrayList;

import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.events.SocketEvent;

public class SocketOutput<T> extends Socket {

    private NodeOutput<T> component;
    private ArrayList<SocketConnection<T>> connections = new ArrayList<>();

    public SocketOutput(NodeOutput<T> component, Class<T> type) {
        super(component);

        this.component = component;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public SocketConnection<T> createLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketInput<T> socketInput = (SocketInput<T>) socket;
            SocketConnection<T> connection = new SocketConnection<>(this, socketInput);
            
            socketInput.setConnection(connection);
            connections.add(connection);
           
            this.fireEvent(new SocketEvent(SocketEvent.OUTPUT_LINKED, connection));

            return connection;
        } else {
            return null;
        }
    }

    public void addConnection(SocketConnection<T> connection) {
        connections.add(connection);
    }

    @Override
    public void updateConnections() {
        for (SocketConnection<T> connection : connections) {
            connection.setStart(getCenter());
        }
    }

    public void removeConnection(SocketConnection<T> connection) {
        if (connections.contains(connection)) {
            connections.remove(connection);
            connection.destroy();
        }
    }

    @Override
    public NodeOutput<T> getSection() {
        return component;
    }

    public void broadcast() {
        for (SocketConnection<T> connection : connections) {
            connection.passValue();
        }
    }
}
