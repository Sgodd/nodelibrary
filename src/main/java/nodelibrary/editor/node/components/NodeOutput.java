package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketOutput;


public class NodeOutput<T> extends NodeSection {
    
    private DataControl<T> dataControl;
    
    private Class<T> type;
    private T        data;

    public NodeOutput(Class<T> type, String labelText, DataControl<T> dataControl) {
        super();
        this.type = type;

        Label label = new Label(labelText);
        this.dataControl = dataControl;    

        grid.add(label, 0, 0);
        grid.add(dataControl, 0, 1);

       
        SocketOutput<T> socket = Socket.out(this, type);
        getChildren().add(socket);

        AnchorPane.setRightAnchor(socket, -9.0);
        AnchorPane.setTopAnchor(socket, 11.0);
    }

}
