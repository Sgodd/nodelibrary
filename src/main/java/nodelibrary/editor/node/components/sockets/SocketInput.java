package nodelibrary.editor.node.components.sockets;

import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.events.SocketEvent;

public class SocketInput<T> extends Socket {

    public final NodeInput<T> component;

    private SocketConnection<T> connection = null;

    public SocketInput(NodeInput<T> component, Class<T> type) {
        super(component);
        
        this.component = component;
        this.type = type;

    }

    @SuppressWarnings("unchecked")
    public void createLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketOutput<T> socketOutput = (SocketOutput<T>) socket;
            SocketConnection<T> connection = new SocketConnection<>(socketOutput, this);

            if (this.connection != null) {
                this.connection.destroy();
            }

            connection.assign();
        }
    }
    
    // TODO: Add to abstract super
    public void disown(SocketConnection<?> connection) {
        if (this.connection.equals(connection)) {
            this.connection = null;
            component.controlDisabled(false);
        }
    }

    public void setConnection(SocketConnection<T> connection) {
        if (this.connection != null) {
            this.connection.destroy();
        }

        if (connection != null) {
            component.controlDisabled(true);
        } else {
            component.controlDisabled(false);
        }

        this.connection = connection;
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

