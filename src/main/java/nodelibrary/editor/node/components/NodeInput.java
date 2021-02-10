package nodelibrary.editor.node.components;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.components.control.DataControl;
import nodelibrary.editor.node.components.sockets.Socket;
import nodelibrary.editor.node.components.sockets.SocketInput;


public class NodeInput<T> extends NodeSection {
    
    private Class<T> type;
    private T        value;

    public NodeInput(Class<T> type, String labelText) {
        super();
        this.type = type;
        
        Label label = new Label(labelText);

        grid.add(label, 0, 0);
       
        SocketInput<T> socket = Socket.in(this, type);
        getChildren().add(socket);

        AnchorPane.setLeftAnchor(socket, -9.0);
        AnchorPane.setTopAnchor(socket, 5.0);
    }



}
