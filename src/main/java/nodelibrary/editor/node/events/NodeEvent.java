package nodelibrary.editor.node.events;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;

public class NodeEvent extends Event {
    
    /**
     *
     */
    private static final long serialVersionUID = 6767454647947577370L;

    public static final EventType<NodeEvent> ANY = 
        new EventType<NodeEvent> (Event.ANY, "NODE_EVENT");


    public static final EventType<NodeEvent> DELETED = 
        new EventType<NodeEvent> (NodeEvent.ANY, "DELETED");

    public NodeEvent(EventType<? extends NodeEvent> eventType) {
        super(eventType);
        this.node = null;
    }

    public NodeEvent(EventType<? extends NodeEvent> eventType, Node node) {
        super(eventType);
        this.node = node;
    }

    public NodeEvent(Object source, EventTarget target, EventType<? extends NodeEvent> eventType) {
        super(source, target, eventType);
    }

    public Node node;
}
