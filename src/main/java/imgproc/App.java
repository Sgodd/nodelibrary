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
import nodelibrary.editor.view.NodeMenu;

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

        NodeMenu input = editor.menu.createSubMenu("Input");
        input.addMenuItem("Image", ImageNode.class);

        NodeMenu output = editor.menu.createSubMenu("Output");
        output.addMenuItem("Viewer", ImageViewerNode.class);

        NodeMenu filters = editor.menu.createSubMenu("Filters");
        filters.addMenuItem("Sobel Filter", SobelFilterNode.class);

        editor.menu.addMenu(input);
        editor.menu.addMenu(output);
        editor.menu.addMenu(filters);

        // editor.addMenuItem("Image", ImageNode.class);
        // editor.addMenuItem("Image Viewer", ImageViewerNode.class);
        // editor.addMenuItem("Threshold", ThresholdNode.class);
        // editor.addMenuItem("Sobel Filter", SobelFilterNode.class);
        // editor.addMenuItem("Dither Node", DitherNode.class);
    }
}