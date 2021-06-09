package imgproc;

import imgproc.functions.filters.SobelFilter;
import imgproc.nodes.ImageNode;
import imgproc.nodes.ImageViewerNode;
import imgproc.nodes.SobelFilterNode;
import imgproc.nodes.ThresholdNode;
import imgproc.nodes.dither.DitherNode;
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

        editor.addMenuItem("Image", ImageNode.class);
        editor.addMenuItem("Image Viewer", ImageViewerNode.class);
        editor.addMenuItem("Threshold", ThresholdNode.class);
        editor.addMenuItem("Sobel Filter", SobelFilterNode.class);
        editor.addMenuItem("Dither Node", DitherNode.class);
    }
}