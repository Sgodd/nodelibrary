package imgproc.nodes.filter;

import imgproc.functions.ImageProcessor;
import imgproc.functions.filters.GaussianBlur;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;
import nodelibrary.editor.node.components.control.IntegerControl;

public class BlurFilterNode extends Node {

    private NodeInput<Image> input;
    private NodeInput<Integer> size;
    private NodeOutput<Image> output;

    public BlurFilterNode(double x, double y) {
        super(x, y, "Blur Filter Node");
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image");
        
        input = input(Image.class, "Image");
        size = input(Integer.class, "Size", new IntegerControl(0, 50, 1, 1));
    }

    @Override
    public void function() {
        ImageProcessor processor = new ImageProcessor(new GaussianBlur(size.getValue()));
        Image image = input.getValue();

        if (image != null) {
            Image out = processor.apply(image);
            output.setValue(out);
        }

    }

}