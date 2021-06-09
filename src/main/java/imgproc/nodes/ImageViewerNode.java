package imgproc.nodes;

import imgproc.nodes.components.ImageSection;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;

public class ImageViewerNode extends Node {

    private NodeInput<Image> image;

    private ImageSection iSection = new ImageSection(this);

    public ImageViewerNode(double x, double y) {
        super(x, y, "Image Viewer");
        
        container.getChildren().add(iSection);
    }

    @Override
    protected void initialize() {
        image = input(Image.class, "Image", null);
    }

    @Override
    protected void function() {
        Image i = image.getValue();
        iSection.view.setImage(i);
    }
    
}
