package nodelibrary.editor.view;

import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.sockets.SocketController;
import nodelibrary.editor.node.events.NodeEvent;

public class EditorCanvas extends AnchorPane {

    public final EditorScene scene;
    private Group nodes;
    // private ContextMenu menu = new ContextMenu();

    private double mx, my = 0;

    public EditorCanvas() {
        scene = new EditorScene();
        nodes = new Group();
        // menu = new NodeMenu(this);

        setTopAnchor(scene, 0.0);
        setBottomAnchor(scene, 0.0);
        setLeftAnchor(scene, 0.0);
        setRightAnchor(scene, 0.0);

        getChildren().addAll(scene, SocketController.MAIN, SocketController.MAIN.guideLine(), nodes);

        scene.setOnContextMenuRequested(e -> {

            // menu.setPos(e.getSceneX(), e.getSceneY());
            // menu.show(scene, e.getScreenX(), e.getScreenY());
            // mx = e.getSceneX();
            // my = e.getSceneY();
            // menu.show(scene, e.getScreenX(), e.getScreenY());

            // e.consume();
        });

        addEventHandler(NodeEvent.DELETED, e -> {

            nodes.getChildren().remove(e.node);

            e.consume();
        });
    }

    // public <T extends Node> void addMenuItem(String itemLabel, Class<T> type) {
    // MenuItem mItem = new MenuItem(itemLabel);

    // mItem.setOnAction(e -> {
    // try {
    // T node = type.getDeclaredConstructor(double.class,
    // double.class).newInstance(mx, my);
    // addNode(node);
    // } catch (Exception err) {
    // err.printStackTrace();
    // }
    // });

    // menu.getItems().add(mItem);
    // }

    public void addNode(Node node) {
        nodes.getChildren().add(node);
    }
}
