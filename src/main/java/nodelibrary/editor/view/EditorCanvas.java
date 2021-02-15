package nodelibrary.editor.view;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.sockets.SocketController;
import nodelibrary.editor.node.events.DataEvent;

public class EditorCanvas extends AnchorPane {
    
    public static double GLOBAL_SCALE = 1;

    private EditorScene      scene;
    private Group            nodes;

    public EditorCanvas() {
        scene = new EditorScene();  
        nodes = new Group();

        setTopAnchor(scene, 0.0);
        setBottomAnchor(scene, 0.0);
        setLeftAnchor(scene, 0.0);
        setRightAnchor(scene, 0.0);

        nodes.addEventHandler(DataEvent.LINK_UPDATE, e -> {
            System.out.println("HELLO I CAUGHT THE EVENT");
            e.consume();
        });

        getChildren().addAll(scene, SocketController.MAIN, SocketController.MAIN.guideLine(), nodes);
    }

    public void addNode(Node node) {
        nodes.getChildren().add(node);
    }
}
