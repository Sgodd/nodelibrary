package nodelibrary.editor.node.components.control;

public class DoubleControl extends NumberControl<Double> {

    public DoubleControl() {
        super(new DoubleInput(-Double.MAX_VALUE, Double.MAX_VALUE, 0.0));
        setValue(0.0);
    }

    public DoubleControl(double min, double max, double init) {
        super(new DoubleInput(min, max, init));
        setValue(0.0);
    }

    @Override
    public Double getValue() {
        return input.getValue();
    }    
}
