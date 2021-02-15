package nodelibrary.editor.node.components.sockets;

import javafx.scene.Group;

public class SocketController extends Group {

    public static final SocketController MAIN = new SocketController();
    private SocketConnection<Void> guideLine = new SocketConnection<>();

    public SocketController() {
    }

    /**
     * Adds a SocketConnection to the group
     * 
     * @param connection
     */
    public void addConnection(SocketConnection<?> connection) {
        if (!getChildren().contains(connection)) {
            getChildren().add(connection);
        }
    }

    /**
     * Removes a SocketConnection from the group
     * 
     * @param connection
     */
    public void removeConnection(SocketConnection<?> connection) {
        if (getChildren().contains(connection)) {
            getChildren().remove(connection);
        }
    }

    /**
     * Returns the guideLine that belongs to the SocketController
     * 
     * @return A SocketConnection for the guideline of the socket controller
     */
    public SocketConnection<Void> guideLine() {
        return guideLine;
    }

}
