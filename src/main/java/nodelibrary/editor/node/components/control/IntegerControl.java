package nodelibrary.editor.node.components.control;

import javafx.scene.layout.HBox;

public class IntegerControl extends DataControl<Integer> {

    private IntegerInput integerInput;

    public IntegerControl() {
        integerInput = new IntegerInput(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

        HBox hbox = new HBox();

        getChildren().add(hbox);
        hbox.getChildren().add(integerInput);        
    }

    public IntegerControl(Integer min, Integer max, Integer init) {
        integerInput = new IntegerInput(min, max, init);

        HBox hbox = new HBox();

        getChildren().add(hbox);
        hbox.getChildren().add(integerInput);
    }

    @Override
    public Integer getValue() {
        return null;
    }

    @Override
    public void disable() {

    }

    @Override
    public void enable() {

    }
    
}
