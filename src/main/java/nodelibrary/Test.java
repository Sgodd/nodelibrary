package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class Test extends Node {
    
    private NodeOutput<Double> result;
    private NodeInput<Double> test;

    public Test(double x, double y) {
        super(x, y, "Times 5 Node");
    }

    protected void initialize() {
        result = output(Double.class, "Result");
        test   = input(Double.class, "Result", new DoubleControl());

        result.setValue(0.0);
    }

    protected void function() {
        result.setValue(test.getValue() * 5);
    }

    @Override
    public String toString() {
        return "Test1 Node";
    }
}
