package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class Test2 extends Node {
    
    private NodeOutput<Double> result;
    private NodeInput<Double> test;
    private NodeInput<Double> test2;

    public Test2(double x, double y) {
        super(x, y, "Test Node");
    }

    protected void initialize() {
        result = output(Double.class, "Result", new DoubleControl());
        test   = input(Double.class, "Result");
        test2  = input(Double.class, "Result2");
    }

    protected void function() {
        Double value = test.getValue() + test2.getValue();
        result.setValue(value);
    }
}
