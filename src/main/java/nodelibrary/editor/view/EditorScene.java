package nodelibrary.editor.view;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditorScene extends Pane {
    
    public EditorScene() {
        String s = getClass().getResource("./Editor.css").toExternalForm();
        getStylesheets().add(s);    
        getStyleClass().add("editor-background");
    }

}
