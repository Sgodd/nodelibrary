package nodelibrary.editor;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nodelibrary.editor.view.EditorCanvas;
import nodelibrary.editor.view.EditorToolBar;

public class Editor {
    

    public Editor(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280, 720);
        

        EditorCanvas canvas = new EditorCanvas();
        EditorToolBar toolbar = new EditorToolBar();

        AnchorPane topAnchor = new AnchorPane(toolbar);
        AnchorPane.setTopAnchor(toolbar, 0.0);
        AnchorPane.setLeftAnchor(toolbar, 0.0);
        AnchorPane.setRightAnchor(toolbar, 0.0);

        root.setTop(topAnchor);
        root.setCenter(canvas);
        root.requestFocus();

        stage.setTitle("Node Editor");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
