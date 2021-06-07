package imgproc.nodes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;

public class ImageViewerNode extends Node {

    private NodeInput<Image> image;

    private ImageView view = new ImageView();

    public ImageViewerNode(double x, double y) {
        super(x, y, "Image Viewer");
        
        container.getChildren().add(view);
    }

    @Override
    protected void initialize() {
        image = input(Image.class, "Image", null);
    }

    @Override
    protected void function() {
        Image i = image.getValue();
        view.setImage(i);
        view.setFitWidth(720);
        view.setFitHeight(720);
    }
    
}
