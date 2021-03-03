package nodelibrary.editor.node.components.control;

import javafx.scene.layout.HBox;

public class DoubleControl extends DataControl<Double> {

    private DoubleInput doubleInput;

    public DoubleControl() {
        doubleInput = new DoubleInput(-Double.MAX_VALUE, Double.MAX_VALUE, 0.0);

        HBox hbox = new HBox();

        getChildren().add(hbox);
        hbox.getChildren().add(doubleInput);        
    }

    public DoubleControl(double min, double max, double init) {
        doubleInput = new DoubleInput(min, max, init);

        HBox hbox = new HBox();

        getChildren().add(hbox);
        hbox.getChildren().add(doubleInput);        
    }

    @Override
    public Double getValue() {
        return null;
    }

    @Override
    public void disable() {

    }

    @Override
    public void enable() {

    }
    
}
