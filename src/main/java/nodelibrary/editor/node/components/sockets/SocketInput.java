package nodelibrary.editor.node.components.sockets;

import nodelibrary.editor.node.components.NodeInput;

public class SocketInput<T> extends Socket {

    private NodeInput<T> component;
    
    private SocketOutput<T> connectedSocket;

    public SocketInput(NodeInput<T> component, Class<T> type) {
        this.component = component;
        this.type = type;
    }
}
