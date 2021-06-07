package imgproc.control;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.control.FileInput;
import nodelibrary.editor.node.events.DataEvent;

public class ImageControl extends DataControl<Image> {

    private FileInput fileInput = new FileInput();
    private Image image;

    public ImageControl() {
        HBox hbox = new HBox();

        String[] extensions = {
            "*.jpg",
            "*.png"
        };

        fileInput.addFilter("Images", extensions);

        hbox.getChildren().add(fileInput);
        getChildren().add(hbox);

        addEventHandler(DataEvent.CONTROL_UPDATE, e -> {
            image = new Image(fileInput.getValue().toURI().toString());
        });
    }

    @Override
    public Image getValue() {
        return image;
    }

    @Override
    public void setValue(Image image) {
        this.image = image;
    }
    
}
