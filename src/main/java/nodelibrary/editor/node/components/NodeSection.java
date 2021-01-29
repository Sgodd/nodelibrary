package nodelibrary.editor.node.components;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public abstract class NodeSection extends AnchorPane {
    
    protected GridPane grid = new GridPane();

    public NodeSection() {
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);

        setMinWidth(50);

        grid.setPadding(new Insets(5, 10, 5, 10));
        grid.setMinWidth(100);
        grid.setVgap(4);
        grid.setHgap(4);

        getStyleClass().add("node-section");
        getChildren().add(grid);
    }
}
