package imgproc.nodes.colour;

import imgproc.functions.ImageProcessor;
import imgproc.functions.colour.Brightness;
import imgproc.functions.colour.Contrast;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class BrightContrastNode extends Node {

    private NodeInput<Image> input;
    private NodeInput<Double> contrast;
    private NodeInput<Double> brightness;

    private NodeOutput<Image> output;

    public BrightContrastNode(double x, double y) {
        super(x, y, "Bright/Contrast Node");
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image");
        
        input = input(Image.class, "Image");      
        brightness = input(Double.class, "Brightness", new DoubleControl(-1, 1, 0, 0.01));
        contrast = input(Double.class, "Contrast", new DoubleControl(-1, 1, 0, 0.01));

    }

    @Override
    public void function() {
        
        ImageProcessor processor = new ImageProcessor(new Brightness(brightness.getValue()));
        Image image = input.getValue();

        if (image != null) {
            image = processor.apply(image);
            processor.setFunction(new Contrast(contrast.getValue()));
            image = processor.apply(image);
            output.setValue(image);
        }

    }
}