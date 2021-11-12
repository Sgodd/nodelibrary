package imgproc.nodes.converter;

import imgproc.functions.ImageProcessor;
import imgproc.functions.converter.AlphaConverter;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class AlphaConverterNode extends Node {
    
    private NodeInput<Image> input;

    private NodeInput<Double> threshold;
    private NodeInput<Double> factor;

    private NodeOutput<Image> output;

    public AlphaConverterNode(double x, double y) {
        super(x, y, "Alpha Converter Node");
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image");
        input = input(Image.class, "Image");
        threshold = input(Double.class, "Threshold", new DoubleControl(0, 1, 0.5, 0.001));
        factor = input(Double.class, "Factor", new DoubleControl(0, 1, 1, 0.001));
    }


    @Override
    public void function() {
        Double threshold = this.threshold.getValue();
        Double factor = this.factor.getValue();

        ImageProcessor processor = new ImageProcessor(new AlphaConverter(threshold, factor));
        Image image = input.getValue();

        if (image != null) {
            Image out = processor.apply(image);
            output.setValue(out);
        }    
    }

}
