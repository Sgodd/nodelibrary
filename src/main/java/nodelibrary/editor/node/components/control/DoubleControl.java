package nodelibrary.editor.node.components.control;

public class DoubleControl extends NumberControl<Double> {

    public DoubleControl() {
        super(new DoubleInput(-Double.MAX_VALUE, Double.MAX_VALUE, 0.0, 1.0));
        setValue(0.0);
    }

    public DoubleControl(double step) {
        super(new DoubleInput(-Double.MAX_VALUE, Double.MAX_VALUE, 0.0, step));
        setValue(0.0);
    }

    public DoubleControl(double init, double step) {
        super(new DoubleInput(-Double.MAX_VALUE, Double.MAX_VALUE, init, step));
        setValue(init);
    }

    public DoubleControl(double min, double max, double init, double step) {
        super(new DoubleInput(min, max, init, step));
        setValue(init);
    }

    @Override
    public Double getValue() {
        return input.getValue();
    }    
}
