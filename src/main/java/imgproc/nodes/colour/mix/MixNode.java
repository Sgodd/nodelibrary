package imgproc.nodes.colour.mix;

import nodelibrary.editor.node.Node;
import javafx.scene.image.Image;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public abstract class MixNode extends Node {

    protected NodeOutput<Image> output;

    protected NodeInput<Double> factorInput;
    protected NodeInput<Image> imageInput;
    protected NodeInput<Image> maskInput;

    public MixNode(double x, double y, String labelText) {
        super(x, y, labelText);
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image");
        factorInput = input(Double.class, "Factor", new DoubleControl(0, 1, 0.5, 0.01));
        imageInput = input(Image.class, "Background");
        maskInput = input(Image.class, "Foreground");
    }
}
