package nodelibrary;

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

        editor.addMenuItem("Double", DoubleOutputNode.class);
        editor.addMenuItem("AddNode", AddNode.class);
        editor.addMenuItem("PrintNode", PrintNode.class);


    }
}