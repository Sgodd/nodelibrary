package imgproc.nodes;

import imgproc.control.ImageControl;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeOutput;

public class ImageNode extends Node {

    private NodeOutput<Image> output;

    public ImageNode(double x, double y) {
        super(x, y, "Image Node");
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image", new ImageControl());        
    }

    @Override
    protected void function() { }
}
