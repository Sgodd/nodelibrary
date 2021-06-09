package nodelibrary;

import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeOutput;
import nodelibrary.editor.node.components.Selector;

public class SelectorNode extends Node {
    
    private NodeOutput<Double> out;

    private Selector<Double> selector;

    public SelectorNode(double x, double y) {
        super(x, y, "Test Node");
    }

    protected void initialize() {
        out = output(Double.class, "Result", null);
        
        selector = new DoubleSelector(this);
        selector.addItem(1.0);
        selector.addItem(2.0);
        selector.addItem(3.0);

        addSection(selector);
    }

    public void function() {
        out.setValue(selector.getValue());
    }

    @Override
    public String toString() {
        return "Test2 Node";
    }
}
