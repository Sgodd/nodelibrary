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

        functionSelector = new FunctionSelector(this);
        container.getChildren().add(functionSelector);

        functionSelector.addFunction(new FloydSteinberg());
        functionSelector.addFunction(new SobelFilter());
    }

    @Override
    protected void initialize() {
        output = output(Image.class, "Image", null);
        input = input(Image.class, "Image", null);
    }

    @Override
    protected void function() {
        Image image = input.getValue();
        output.setValue(functionSelector.getSelected().apply(image));
    }
    
}
