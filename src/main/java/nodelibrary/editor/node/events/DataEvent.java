package nodelibrary.editor.node.events;

import javafx.event.EventTarget;
import javafx.event.EventType;
import nodelibrary.editor.node.Node;

public class DataEvent extends NodeEvent {

    /**
     *
     */
    private static final long serialVersionUID = 6767454647947577370L;

    public static final EventType<DataEvent> ANY =
        new EventType<DataEvent>(NodeEvent.ANY, "DATA_EVENT");

    public static final EventType<DataEvent> LINK_UPDATE =
        new EventType<DataEvent>(DataEvent.ANY, "LINK_UPDATE");

    
    public DataEvent(Object source, EventTarget target, EventType<? extends NodeEvent> eventType, Node changedNode) {
        super(source, target, eventType);

        this.changedNode = changedNode;
    }

    public DataEvent(EventType<? extends NodeEvent> eventType, Node changedNode) {
        super(eventType);

        this.changedNode = changedNode;
    }

    private Node changedNode;

    public Node getNode() {
        return changedNode;
    }

}
