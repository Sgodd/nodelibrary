package nodelibrary.editor.node.components.sockets;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SocketController extends Group {

    public static final SocketController MAIN = new SocketController();
    private SocketConnection guideLine = new SocketConnection();

    public SocketController() {
    }

    /**
     * Adds a SocketConnection to the group
     * 
     * @param connection
     */
    public void addConnection(SocketConnection connection) {
        if (!getChildren().contains(connection)) {
            getChildren().add(connection);
        }
    }

    /**
     * Removes a SocketConnection from the group
     * 
     * @param connection
     */
    public void removeConnection(SocketConnection connection) {
        if (getChildren().contains(connection)) {
            getChildren().remove(connection);
        }
    }

    /**
     * Returns the guideLine that belongs to the SocketController
     * 
     * @return A SocketConnection for the guideline of the socket controller
     */
    public SocketConnection guideLine() {
        return guideLine;
    }

}
