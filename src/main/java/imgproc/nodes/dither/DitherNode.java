package imgproc.nodes.dither;

import imgproc.functions.ImageFunction;
import imgproc.functions.dither.FloydSteinberg;
import imgproc.functions.filters.SobelFilter;
import imgproc.nodes.components.FunctionSelector;
import javafx.scene.image.Image;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;

public class DitherNode extends Node {

    private NodeInput<Image> input;
    private NodeOutput<Image> output;

    private FunctionSelector functionSelector;

    public DitherNode(double x, double y) {
        super(x, y, "Dither Node");

    }

    @Override
    protected void initialize() {
        functionSelector = new FunctionSelector(this);

        functionSelector.addFunction(new FloydSteinberg());
        functionSelector.addFunction(new SobelFilter());

        output = output(Image.class, "Image", null);
        addSection(functionSelector);
        input = input(Image.class, "Image", null);
    }

    @Override
    public void function() {
        Image image = input.getValue();
        output.setValue(functionSelector.getSelected().apply(image));
    }
    
}
