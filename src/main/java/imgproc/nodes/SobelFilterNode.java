package imgproc.nodes;

import imgproc.functions.filters.SobelFilter;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;

public class SobelFilterNode extends Node {

    private NodeInput<Image> input;
    private NodeOutput<Image> output;

    public SobelFilterNode(double x, double y) {
        super(x, y, "Sobel Filter Node");
    }

    @Override
    protected void initialize() {
        input = input(Image.class, "Image", null);
        output = output(Image.class, "Image", null);
    }

    @Override
    protected void function() {
        SobelFilter filter = new SobelFilter();
        Image image = input.getValue();
        Image out = filter.apply(image);

        output.setValue(out);
    }
    
}
