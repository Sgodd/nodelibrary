package imgproc;

import imgproc.nodes.ImageNode;
import imgproc.nodes.ImageViewerNode;
import imgproc.nodes.SobelFilterNode;
import imgproc.nodes.ThresholdNode;
import javafx.application.Application;
import javafx.stage.Stage;
import nodelibrary.editor.Editor;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

    public void start(Stage stage) {
        Editor editor = new Editor(stage);

        ImageNode node = new ImageNode(50,50);
        ImageViewerNode viewer1 = new ImageViewerNode(400, 50);
        ImageViewerNode viewer2 = new ImageViewerNode(900, 150);
        SobelFilterNode thresholder = new SobelFilterNode(200, 100);

        editor.addNode(node);

        editor.addNode(thresholder);
        editor.addNode(viewer1);
        editor.addNode(viewer2);
    }
}