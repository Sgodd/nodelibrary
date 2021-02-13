package nodelibrary.editor.node.components.sockets;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;

public abstract class Socket extends Circle {
    
    public static final double RADIUS = 7;
    private static SocketConnection guideLine = new SocketConnection();

    protected Class<?> type;

    public Socket() {
        super(RADIUS);

        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);

        initHandlers();
    }

    private void initHandlers() {
        setOnMousePressed(e -> {
            guideLine.setStart(localToScene(0, 0));
            guideLine.setEnd(new Point2D(e.getSceneX(), e.getSceneY()));
            guideLine.setVisible(true);
            e.consume();
        });

        setOnDragDetected(e -> {
            startFullDrag();
        });

        setOnMouseDragged(e -> {
            guideLine.setEnd(new Point2D(e.getSceneX(), e.getSceneY()));
            e.consume();
        });

        setOnMouseDragOver(e -> {
            Object source = e.getGestureSource();
            
            if (isSocket(source) && source != this) {
                if (socketTest((Socket) source, this)) {
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

        });

        setOnMouseReleased(e -> {
            guideLine.setVisible(false);
        });
    }

    private boolean isSocket(Object o) {
        return Socket.class.isAssignableFrom(o.getClass());
    }

    private static <T> boolean socketTest(Socket s1, Socket s2) {

        if (s1.getClass() == s2.getClass()) {
            return false;
        } 

        if (s1.type == s2.type) {
            return true;
        } else {
            return false;
        }
    }


    public Point2D getLocation() {
        return localToScene(0, 0);
    }

    public static <T> SocketInput<T> in(NodeInput<T> component, Class<T> type) {
        return new SocketInput<T>(component, type);
    }

    public static <T> SocketOutput<T> out(NodeOutput<T> component, Class<T> type) {
        return new SocketOutput<T>(component, type);
    }

    public static SocketConnection guideLine() {
        return guideLine;
    }

}
