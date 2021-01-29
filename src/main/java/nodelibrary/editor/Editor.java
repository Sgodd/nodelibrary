package nodelibrary.editor;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nodelibrary.editor.view.EditorCanvas;
import nodelibrary.editor.view.EditorToolBar;

public class Editor {
    

    public Editor(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280, 720);
        

        EditorToolBar toolbar = new EditorToolBar();

        AnchorPane topAnchor = new AnchorPane(toolbar);
        topAnchor.setTopAnchor(toolbar, 0.0);
        topAnchor.setLeftAnchor(toolbar, 0.0);
        topAnchor.setRightAnchor(toolbar, 0.0);

        EditorCanvas canvas = new EditorCanvas();

        root.setTop(topAnchor);
        root.setCenter(canvas);
        root.requestFocus();

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();


    }
}
