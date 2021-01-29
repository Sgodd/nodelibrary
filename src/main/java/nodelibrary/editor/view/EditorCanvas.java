package nodelibrary.editor.view;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import nodelibrary.editor.node.Node;

public class EditorCanvas extends AnchorPane {
    
    public static double GLOBAL_SCALE = 1;


    private EditorScene scene;
    private Group       nodes;

    public EditorCanvas() {
        scene = new EditorScene();  
        nodes = new Group();
        
        setTopAnchor(scene, 0.0);
        setBottomAnchor(scene, 0.0);
        setLeftAnchor(scene, 0.0);
        setRightAnchor(scene, 0.0);

        getChildren().addAll(scene, nodes);
    }

    public void addNode(Node node) {
        nodes.getChildren().add(node);
    }
}
