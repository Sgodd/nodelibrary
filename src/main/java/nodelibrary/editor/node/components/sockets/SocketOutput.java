package nodelibrary.editor.node.components.sockets;

import java.util.ArrayList;

import nodelibrary.editor.node.components.NodeOutput;

public class SocketOutput<T> extends Socket {

    public final NodeOutput<T> component;
    private ArrayList<SocketConnection<T>> connections = new ArrayList<>();

    public SocketOutput(NodeOutput<T> component, Class<T> type) {
        super(component);

        this.component = component;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public void createLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketInput<T> socketInput = (SocketInput<T>) socket;
            SocketConnection<T> connection = new SocketConnection<>(this, socketInput);

            connection.assign();
        }
    }

    @Override
    public void updateConnections() {
        for (SocketConnection<T> connection : connections) {
            connection.setStart(getCenter());
        }
    }

    // TODO: Add to abstract super
    public void disown(SocketConnection<?> connection) {
        if (this.connections.contains(connection)) {
            this.connections.remove(connection);
        }
    }

    public void addConnection(SocketConnection<T> connection) {
        if (!this.connections.contains(connection)) {
            this.connections.add(connection);
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
