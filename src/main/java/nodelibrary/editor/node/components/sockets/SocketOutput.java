package nodelibrary.editor.node.components.sockets;

import nodelibrary.editor.node.components.NodeOutput;

public class SocketOutput<T> extends Socket {

    private NodeOutput<T> component;

    public SocketOutput(NodeOutput<T> component, Class<T> type) {
        this.component = component;
        this.type = type;
    }

}

