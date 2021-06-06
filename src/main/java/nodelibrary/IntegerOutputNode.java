package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.IntegerControl;

public class IntegerOutputNode extends Node {

    private NodeOutput<Integer> output; 

    public IntegerOutputNode(double x, double y) {
        super(x, y, "Integer Output Node");
    }

    @Override
    protected void initialize() {
        output = output(Integer.class, "Output", new IntegerControl());
    }

    @Override
    protected void function() {

    }

    @Override
    public String toString() {
        return "Integer Output Node";
    }
    
    
}
