package nodelibrary.editor.node.components.control;

import java.io.File;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import nodelibrary.editor.node.events.DataEvent;

public class FileInput extends DataInput<File> {

    private File file;
    private FileChooser fc = new FileChooser();

    public FileInput() {
        Button fileChooser = new Button("Choose File");
        fileChooser.setOnAction(e -> {
            File temp = file;
            file = fc.showOpenDialog(getScene().getWindow());
            if (file == null) {
                file = temp;
            }
            fireEvent(new DataEvent(DataEvent.CONTROL_UPDATE));
        });

        getChildren().add(fileChooser);
    }

    public void addFilter(String filterName, String[] exts) {
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(filterName, exts));
    }

    @Override
    public File getValue() {
        return file;
    }

    @Override
    public void setValue(File file) {
        this.file = file;        
    }
    
}
