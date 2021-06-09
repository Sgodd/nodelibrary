package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class DoubleOutputNode extends Node {

    private NodeOutput<Double> output; 

    public DoubleOutputNode(double x, double y) {
        super(x, y, "Double Output Node");
    }

    @Override
    protected void initialize() {
        output = output(Double.class, "Output", new DoubleControl());
    }

    @Override
    public void function() {

    }

    @Override
    public String toString() {
        return "Double Output Node";
    }
    
    
}
