package nodelibrary.editor.node.components.sockets;

import nodelibrary.editor.node.components.NodeInput;

public class SocketInput<T> extends Socket {

    private NodeInput<T> component;
    
    private SocketConnection connection = null;

    public SocketInput(NodeInput<T> component, Class<T> type) {
        this.component = component;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public SocketConnection createLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketOutput<T> socketOutput = (SocketOutput<T>) socket;
            SocketConnection connection = new SocketConnection(socketOutput, this);


            if (this.connection != null) {
                SocketController.MAIN.removeConnection(this.connection);
            }

            this.connection = connection;
            SocketController.MAIN.addConnection(this.connection);

            socketOutput.addConnection(this.connection);

            return connection;
        } else {
            return null;
        }
    }

    public void setConnection(SocketConnection connection) {
        SocketController.MAIN.getChildren().remove(this.connection);
        this.connection = connection;

        System.out.println("TEST");
    }

    @Override
    public void updateConnections() {
        if (connection != null) {
            connection.setEnd(getCenter());
        }
    }
}

