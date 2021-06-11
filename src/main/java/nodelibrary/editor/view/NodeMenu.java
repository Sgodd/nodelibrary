package nodelibrary.editor.view;

import javafx.geometry.Point2D;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import nodelibrary.editor.node.Node;

public class NodeMenu extends Menu {
    
    private NodeContextMenu parent;

    public NodeMenu(NodeContextMenu parent, String name) {
        super(name);

        this.parent = parent;
    }


    public <T extends Node> void addMenuItem(String itemLabel, Class<T> type) {
        MenuItem mItem = new MenuItem(itemLabel);

        mItem.setOnAction(e -> {
            try {
                Point2D pos = parent.getPos();
                T node = type.getDeclaredConstructor(double.class, double.class).newInstance(pos.getX(), pos.getY());
                parent.canvas.addNode(node);
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        getItems().add(mItem);
    }
}
