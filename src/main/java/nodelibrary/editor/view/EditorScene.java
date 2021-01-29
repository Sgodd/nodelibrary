package nodelibrary.editor.view;

import javafx.scene.layout.Pane;

public class EditorScene extends Pane {
    
    public EditorScene() {
        String s = getClass().getResource("./Editor.css").toExternalForm();
        getStylesheets().add(s);    
        getStyleClass().add("editor-background");
    }

}
