package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;
import nodelibrary.editor.node.components.control.IntegerControl;

public class Test3 extends Node {
    
    private NodeOutput<Double> result;
    private NodeInput<Integer> test;
    private NodeInput<Double> test2;

    public Test3(double x, double y) {
        super(x, y, "Test Node");
    }

    protected void initialize() {
        result = output(Double.class, "Result", null);
        test   = input(Integer.class, "Result", new IntegerControl(-5,255,0));
        test2  = input(Double.class, "Result2", new DoubleControl(-5,255,0));
    }

    public void function() {
        Double value = test.getValue() + test2.getValue();
        result.setValue(value);
    }

    @Override
    public String toString() {
        return "Test2 Node";
    }
}
