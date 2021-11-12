package nodelibrary;

import imgproc.functions.ImageMixFunction;
import javafx.scene.paint.Color;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;

public class ColourMixNode extends Node {
    private NodeInput<Color> colour1;    
    private NodeInput<Color> colour2;
    private NodeOutput<Color> output; 

    public ColourMixNode(double x, double y) {
        super(x, y, "Colour Mix Node");
    }

    @Override
    protected void initialize() {
        colour1 = input(Color.class, "Colour 1", new ColorControl());        
        colour2 = input(Color.class, "Colour 2", new ColorControl());        
        output = output(Color.class, "Output");
    }

    @Override
    public void function() {
        Color col1 = colour1.getValue();
        Color col2 = colour2.getValue();
        Color out = ImageMixFunction.blend(col1, col2, 0);
        
        output.setValue(out);
   }
}


