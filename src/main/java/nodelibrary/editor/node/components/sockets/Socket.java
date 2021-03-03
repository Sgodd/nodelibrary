package nodelibrary.editor.node.components.sockets;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.NodeSection;

public abstract class Socket extends Circle {
    
    public static final double RADIUS = 7;

    protected Class<?> type;
    protected final NodeSection component;

    public Socket(NodeSection component) {
        super(RADIUS);

        this.component = component;

        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);

        initHandlers();
    }

    private void initHandlers() {
        SocketConnection<Void> guideLine = SocketController.MAIN.guideLine();

        setOnMousePressed(e -> {
            guideLine.setStart(localToScene(0, 0));
            guideLine.setEnd(new Point2D(e.getSceneX(), e.getSceneY()));
            guideLine.setVisible(true);
            e.consume();
        });

        setOnDragDetected(e -> {
            if (this.getClass() == SocketInput.class) {
                SocketInput<?> socket = (SocketInput<?>) this;
                socket.setConnection(null);
            }

            startFullDrag();
        });

        setOnMouseDragged(e -> {
            guideLine.setEnd(new Point2D(e.getSceneX(), e.getSceneY()));
            e.consume();
        });

        setOnMouseDragOver(e -> {
            Object source = e.getGestureSource();
            
            if (isSocket(source) && source != this) {
                if (isCompatible((Socket) source, this)) {
                    guideLine.setStroke(Color.GOLDENROD);
                    guideLine.setEnd(localToScene(0,0));
                }
            }

            e.consume();
        });

        setOnMouseDragExited(e -> {
            guideLine.setStroke(Color.BLACK);
            e.consume();
        });

        setOnMouseDragReleased(e -> {
            Object source = e.getGestureSource();
            if (isSocket(source)) {
                Socket socket = (Socket) source;
                this.createLink(socket);

                this.component.getNode().updateSockets();
            }
        });

        setOnMouseReleased(e -> {
            guideLine.setVisible(false);
        });
    }

    protected boolean isSocket(Object o) {
        return Socket.class.isAssignableFrom(o.getClass());
    }

    protected static boolean isCompatible(Socket s1, Socket s2) {

        if (s1.getClass() == s2.getClass()) {
            return false;
        } 

        if (s1.type.isAssignableFrom(s2.type) || s2.type.isAssignableFrom(s1.type)) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void createLink(Socket socket);
    public abstract void updateConnections();
    public abstract void disown(SocketConnection<?> connection);

    public abstract NodeSection getSection();

    public Point2D getCenter() {
        return localToScene(0, 0);
    }

    public static <T> SocketInput<T> in(NodeInput<T> component, Class<T> type) {
        return new SocketInput<T>(component, type);
    }

    public static <T> SocketOutput<T> out(NodeOutput<T> component, Class<T> type) {
        return new SocketOutput<T>(component, type);
    }
}
