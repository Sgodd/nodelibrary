package nodelibrary.editor.view;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.sockets.SocketController;
import nodelibrary.editor.node.events.NodeEvent;

public class EditorCanvas extends AnchorPane {

    public final EditorScene scene;
    private Group nodes;

    public EditorCanvas() {
        scene = new EditorScene();
        nodes = new Group();

        AnchorPane.setTopAnchor(scene, 0.0);
        AnchorPane.setBottomAnchor(scene, 0.0);
        AnchorPane.setLeftAnchor(scene, 0.0);
        AnchorPane.setRightAnchor(scene, 0.0);

        getChildren().addAll(scene, SocketController.MAIN, SocketController.MAIN.guideLine(), nodes);

        addEventHandler(NodeEvent.DELETED, e -> {
            nodes.getChildren().remove(e.node);

            e.consume();
        });

        scene.setOnMouseDragged(e -> {
            
        });
    }

    public void addNode(Node node) {
        nodes.getChildren().add(node);
    }
}
