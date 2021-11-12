package imgproc.nodes.colour;

import nodelibrary.editor.node.Node;
import imgproc.functions.ImageProcessor;
import imgproc.functions.colour.Gamma;
import javafx.scene.image.Image;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class GammaNode extends Node {
      
    private NodeInput<Image> input;
    private NodeInput<Double> gamma;

    private NodeOutput<Image> output;

    public GammaNode(double x, double y) {
        super(x, y, "Gamma Node");
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image");
        
        input = input(Image.class, "Image");      
        gamma = input(Double.class, "Gamma", new DoubleControl(1,0.01));
    }

    @Override
    public void function() {
        ImageProcessor processor = new ImageProcessor(new Gamma(gamma.getValue()));
        Image image = input.getValue();

        if (image != null) {
            image = processor.apply(image);
            output.setValue(image);
        }

    }
}
