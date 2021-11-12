package nodelibrary.editor.view;

import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import nodelibrary.editor.node.Node;

public class NodeContextMenu extends ContextMenu {

    public final EditorCanvas canvas;

    private double mx = 0;
    private double my = 0;

    public NodeContextMenu(EditorCanvas canvas) {
        this.canvas = canvas;
    }

    public NodeMenu createSubMenu(String name) {
        return new NodeMenu(this, name);
    }

    public void addMenu(Menu menu) {
        getItems().add(menu);
    }
    
    public void setPos(double x, double y) {
        mx = x;
        my = y;
    }

    public Point2D getPos() {
        return new Point2D(mx, my);
    }
}
