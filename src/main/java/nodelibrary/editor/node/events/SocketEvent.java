package nodelibrary.editor.node.events;

import javafx.event.EventType;
import nodelibrary.editor.node.components.sockets.SocketConnection;

public class SocketEvent extends NodeEvent {
    /**
     *
     */
    private static final long serialVersionUID = 6767454647947577370L;

    public static final EventType<SocketEvent> SOCKET_EVENT = 
        new EventType<SocketEvent> (NodeEvent.ANY, "SOCKET_EVENT");

    public static final EventType<SocketEvent> INPUT_LINKED = 
        new EventType<SocketEvent> (SocketEvent.SOCKET_EVENT, "INPUT_LINKED");

    public static final EventType<SocketEvent> OUTPUT_LINKED = 
        new EventType<SocketEvent> (SocketEvent.SOCKET_EVENT, "OUTPUT_LINKED");

    public SocketEvent(EventType<? extends NodeEvent> eventType, SocketConnection connection) {
        super(eventType);

        this.connection = connection;
    }

    public final SocketConnection connection;
}
