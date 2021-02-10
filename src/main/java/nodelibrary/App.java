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

        Test test = new Test(100, 100);
        Test test2 = new Test(100, 100);
        editor.addNode(test);
        editor.addNode(test2);
    }
}