package nodelibrary.editor;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.view.EditorCanvas;
import nodelibrary.editor.view.EditorToolBar;

public class Editor {
    
    EditorCanvas canvas;

    public Editor(Stage stage) {
        AnchorPane root = new AnchorPane();
        
        Scene scene = new Scene(root, 1280, 720);
        

        canvas = new EditorCanvas();
        EditorToolBar toolbar = new EditorToolBar();

        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);

        AnchorPane.setTopAnchor(toolbar, 0.0);
        AnchorPane.setLeftAnchor(toolbar, 0.0);
        AnchorPane.setRightAnchor(toolbar, 0.0);

        root.getChildren().addAll(canvas, toolbar);
        root.requestFocus();

        stage.setTitle("Node Editor");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        String s = getClass().getResource("./Style.css").toExternalForm();
        scene.getStylesheets().add(s);
    }

    public <T extends Node> void addMenuItem(String itemLabel, Class<T> type) {
        canvas.addMenuItem(itemLabel, type);
    }

    public void addNode(Node node) {
        canvas.addNode(node);
    }

    public void addNodes(Node... nodes) {
        for (Node node : nodes) {
            canvas.addNode(node);
        }
    }
}
