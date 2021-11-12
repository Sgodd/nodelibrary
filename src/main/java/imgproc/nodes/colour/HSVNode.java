package imgproc.nodes.colour;

import imgproc.functions.ImageProcessor;
import imgproc.functions.colour.HSV;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class HSVNode extends Node {
    
    private NodeInput<Image> input;
    private NodeInput<Double> hue;
    private NodeInput<Double> saturation;
    private NodeInput<Double> brightness;

    private NodeOutput<Image> output;

    public HSVNode(double x, double y) {
        super(x, y, "HSV Node");
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image");
        
        input = input(Image.class, "Image");      
        hue = input(Double.class, "Hue", new DoubleControl(0, 360, 0, 1));
        saturation = input(Double.class, "Saturation", new DoubleControl(-1, 1, 0, 0.01));
        brightness = input(Double.class, "Brightness", new DoubleControl(-1, 1, 0, 0.01));
    }

    @Override
    public void function() {
        double h = hue.getValue();
        double s = saturation.getValue();
        double b = brightness.getValue();

        ImageProcessor processor = new ImageProcessor(new HSV(h,s,b));
        Image image = input.getValue();

        if (image != null) {
            image = processor.apply(image);
            output.setValue(image);
        }

    }
}
