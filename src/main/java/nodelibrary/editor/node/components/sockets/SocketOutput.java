package nodelibrary.editor.node.components.sockets;

import java.util.ArrayList;

import nodelibrary.editor.node.components.NodeOutput;

public class SocketOutput<T> extends Socket {

    private NodeOutput<T> component;
    private ArrayList<SocketOutput<T>> connectedSockets;

    public SocketOutput(NodeOutput<T> component, Class<T> type) {
        this.component = component;
        this.type = type;

        initHandlers();
    }

    public void initHandlers() {
        setOnMouseDragReleased(e -> {
            Object source = e.getGestureSource();
            if (isSocket(source)) {
                addLink((Socket) source);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void addLink(Socket socket) {
        if (isCompatible(this, socket)) {
            SocketOutput<T> socketOutput = (SocketOutput<T>) socket;
            connectedSockets.add(socketOutput);
        }
    }

}

