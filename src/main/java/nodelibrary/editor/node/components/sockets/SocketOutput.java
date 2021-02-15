package nodelibrary.editor.node.components.sockets;

import java.util.ArrayList;

import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.NodeSection;

public class SocketOutput<T> extends Socket {

    private NodeOutput<T> component;
    private ArrayList<SocketConnection> connections = new ArrayList<>();

    public SocketOutput(NodeOutput<T> component, Class<T> type) {
        super(component);
        
        this.component = component;
        this.type = type;
    }
    
    @SuppressWarnings("unchecked")
    public SocketConnection createLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketInput<T> socketInput = (SocketInput<T>) socket;
            SocketConnection connection = new SocketConnection(this, socketInput);

            socketInput.setConnection(connection);
            connections.add(connection);

            return connection;
        } else {
            return null;
        }
    }

    public void addConnection(SocketConnection connection) {
        connections.add(connection);
    }

    @Override
    public void updateConnections() {
        for (SocketConnection connection : connections) {
            connection.setStart(getCenter());
        }
    }

    @Override
    public NodeSection getSection() {
        return component;
    }
}

