package nodelibrary.editor.node.events;

import javafx.event.EventType;

public class DataEvent extends NodeEvent {

    /**
     *
     */
    private static final long serialVersionUID = 6767454647947577370L;

    public static final EventType<DataEvent> DATA_EVENT = 
        new EventType<DataEvent> (NodeEvent.ANY, "DATA_EVENT");

    public static final EventType<DataEvent> INPUT_UPDATE = 
        new EventType<DataEvent> (DataEvent.DATA_EVENT, "INPUT_UPDATE");

    public static final EventType<DataEvent> OUTPUT_UPDATE = 
        new EventType<DataEvent> (DataEvent.DATA_EVENT, "OUTPUT_UPDATE");

    public DataEvent(EventType<? extends NodeEvent> eventType) {
        super(eventType);
    }
    
}
