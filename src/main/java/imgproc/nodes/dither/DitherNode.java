package imgproc.nodes.dither;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import imgproc.functions.dither.FloydSteinberg;
import imgproc.functions.dither.RandomDither;
import imgproc.functions.filters.SobelFilter;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.Selector;

public class DitherNode extends Node {

    private NodeInput<Image> input;
    private NodeOutput<Image> output;

    private Selector<ImageFunction> functionSelector;

    public DitherNode(double x, double y) {
        super(x, y, "Dither Node");
    }

    @Override
    protected void initialize() {
        functionSelector = new Selector<>(this);
        
        functionSelector.addItem(new FloydSteinberg());
        functionSelector.addItem(new RandomDither());

        output = output(Image.class, "Image", null);
        addSection(functionSelector);
        input = input(Image.class, "Image", null);
    }

    @Override
    public void function() {
        ImageProcessor processor = new ImageProcessor(functionSelector.getValue());
        Image image = input.getValue();
        if (image != null) {
            output.setValue(processor.apply(image));
        }
    }
    
}
