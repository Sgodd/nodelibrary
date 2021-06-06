package nodelibrary.editor.node.components.control;

public class IntegerControl extends NumberControl<Integer> {

    public IntegerControl() {
        super(new IntegerInput(Integer.MIN_VALUE, Integer.MAX_VALUE, 0));
        setValue(0);
    }

    public IntegerControl(Integer min, Integer max, Integer init) {
        super(new IntegerInput(min, max, init));
        setValue(0);
    }

    @Override
    public Integer getValue() {
        return input.getValue();
    }    
}
