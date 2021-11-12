package nodelibrary;

import imgproc.nodes.ThresholdNode;
import imgproc.nodes.dither.DitherNode;
import javafx.application.Application;
import javafx.scene.control.Menu;
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



    }
}