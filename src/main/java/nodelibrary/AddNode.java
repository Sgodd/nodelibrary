package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.control.DoubleControl;

public class AddNode extends Node {

    private NodeInput<Double> num1;    
    private NodeInput<Double> num2;
    private NodeOutput<Double> output; 

    public AddNode(double x, double y) {
        super(x, y, "Add Node");
    }

    @Override
    protected void initialize() {
        num1 = input(Double.class, "Value 1", new DoubleControl());        
        num2 = input(Double.class, "Value 2", new DoubleControl());        
        output = output(Double.class, "Output", null);
    }

    @Override
    public void function() {
        Double v1 = num1.getValue();
        Double v2 = num2.getValue();
        
        output.setValue(v1 + v2);
    }

    @Override
    public String toString() {
        return "Add Node";
    }
    
    
}
