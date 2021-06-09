package nodelibrary.editor.node.components.sockets;

import java.util.ArrayList;

import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.events.SocketEvent;

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

            this.fireEvent(new SocketEvent(SocketEvent.OUTPUT_UNLINKED, connection));

        }
    }

    public void addConnection(SocketConnection<T> connection) {
        if (!this.connections.contains(connection)) {
            this.connections.add(connection);
        }

        this.fireEvent(new SocketEvent(SocketEvent.OUTPUT_LINKED, connection));

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

    /**
     * Removes all connections assosciated with the socket and destroys them.
     */
    public void destroy() {
        int size = connections.size();

        // Removing connections from arraylist while iterating is not safe therefore done this way.
        for (int i = 0; i < size; i++) {
            connections.get(0).destroy();
        }
    }
}
