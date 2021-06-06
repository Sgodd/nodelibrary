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

        DoubleOutputNode don1 = new DoubleOutputNode(100, 100);
        DoubleOutputNode don2 = new DoubleOutputNode(100, 250);
        editor.addNode(don1);
        editor.addNode(don2);

        IntegerOutputNode ion1 = new IntegerOutputNode(100, 100);
        IntegerOutputNode ion2 = new IntegerOutputNode(100, 250);
        editor.addNode(ion1);
        editor.addNode(ion2);

        AddNode an1 = new AddNode(300, 125);
        editor.addNode(an1);

        PrintNode pn1 = new PrintNode(500, 125);
        editor.addNode(pn1);



        // for (int i = 0; i < 5; i++) {
        //     Test test = new Test(100, 2*i*100);
        //     editor.addNode(test);
        // }

        // Test2 test2 = new Test2(300, 2*100);
        // editor.addNode(test2);

        // Test3 test3 = new Test3(300, 4*100);
        // editor.addNode(test3);
    }
}