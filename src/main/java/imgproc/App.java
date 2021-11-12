package imgproc;

import imgproc.nodes.ImageNode;
import imgproc.nodes.ImageViewerNode;
import imgproc.nodes.filter.BlurFilterNode;
import imgproc.nodes.filter.SobelFilterNode;
import imgproc.nodes.colour.BrightContrastNode;
import imgproc.nodes.colour.GammaNode;
import imgproc.nodes.colour.HSVNode;
import imgproc.nodes.colour.mix.MixLightenNode;
import imgproc.nodes.colour.mix.MixMultiplyNode;
import imgproc.nodes.colour.mix.MixNormalNode;
import imgproc.nodes.converter.AlphaConverterNode;
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

        NodeMenu colour = editor.menu.createSubMenu("Colour");
        colour.addMenuItem("Brightness/Contrast", BrightContrastNode.class);
        colour.addMenuItem("HSV", HSVNode.class);
        colour.addMenuItem("Gamma", GammaNode.class);

        NodeMenu mix = new NodeMenu(editor.menu, "Mix");
        mix.addMenuItem("Normal", MixNormalNode.class);
        mix.addMenuItem("Multiply", MixMultiplyNode.class);
        mix.addMenuItem("Lighten", MixLightenNode.class);

        colour.addSubMenu(mix);

        // output.addMenuItem("Viewer", ImageViewerNode.class);

        NodeMenu conversion = editor.menu.createSubMenu("Conversion");
        conversion.addMenuItem("Alpha Converter", AlphaConverterNode.class);

        NodeMenu filters = editor.menu.createSubMenu("Filters");
        filters.addMenuItem("Sobel Filter", SobelFilterNode.class); 
        filters.addMenuItem("Blur Filter", BlurFilterNode.class); 

        editor.menu.addMenu(input);
        editor.menu.addMenu(output);
        editor.menu.addMenu(colour);
        editor.menu.addMenu(conversion);
        editor.menu.addMenu(filters);

    }
}