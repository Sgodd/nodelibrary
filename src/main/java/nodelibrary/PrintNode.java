package nodelibrary;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;

public class PrintNode extends Node {
    
    private NodeInput<Object> test;

    private Label outputText = new Label("");;

    public PrintNode(double x, double y) {
        super(x, y, "Printer");

        HBox hbox = new HBox();
        hbox.getChildren().add(outputText);
        container.getChildren().add(hbox);
    }

    protected void initialize() {
        test = input(Object.class, "Input");
    }

    protected void function() {
        outputText.setText(test.getValue().toString());
    }
}
