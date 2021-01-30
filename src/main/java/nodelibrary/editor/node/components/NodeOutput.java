package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import nodelibrary.editor.node.components.control.DataControl;

public class NodeOutput<T> extends NodeSection {
    
    private DataControl<T> dataControl;

    public NodeOutput(String labelText, DataControl<T> dataControl) {
        super();

        Label label = new Label(labelText);
        this.dataControl = dataControl;    

        grid.add(label, 0, 0);
        grid.add(dataControl, 0, 1);

        Circle circle = new Circle(0,0, 5);
        circle.setFill(Color.WHITE);
        getChildren().add(circle);

        AnchorPane.setRightAnchor(circle, -6.0);
        AnchorPane.setTopAnchor(circle, 8.0);

        
    }



}
