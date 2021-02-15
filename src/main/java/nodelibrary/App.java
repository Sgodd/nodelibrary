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

        for (int i = 0; i < 5; i++) {
            Test test = new Test(100, 2*i*100);
            editor.addNode(test);
        }

        Test2 test = new Test2(300, 2*100);
        editor.addNode(test);
    }
}