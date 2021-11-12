package nodelibrary.editor.node.components.control;

public class IntegerControl extends NumberControl<Integer> {

    public IntegerControl() {
        super(new IntegerInput(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1));
        setValue(0);
    }

    public IntegerControl(Integer min, Integer max, Integer init, Integer step) {
        super(new IntegerInput(min, max, init, step));
        setValue(init);
    }

    @Override
    public Integer getValue() {
        return input.getValue();
    }    
}
