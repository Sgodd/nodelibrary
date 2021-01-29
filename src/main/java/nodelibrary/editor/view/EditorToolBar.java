package nodelibrary.editor.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class EditorToolBar extends ToolBar {
    
    public EditorToolBar() {
        Button file = new Button("File");
        Button edit = new Button("Edit");

        getItems().addAll(file, edit);
    }
}
