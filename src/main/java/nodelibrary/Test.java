package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class Test extends Node {
    
    private NodeOutput<Double> result;

    public Test() {
        super(300, 300, "Test Node");
    }

    protected void initialize() {
        result = new NodeOutput<Double>("Result", new DoubleControl());

        outputs = new NodeOutput[] {result};
    }

    protected void function() {
        
    }
}
