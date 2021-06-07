package imgproc.nodes;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeInput;
import nodelibrary.editor.node.components.NodeOutput;

public class ThresholdNode extends Node {

    private NodeInput<Image> input;
    private NodeOutput<Image> output;

    public ThresholdNode(double x, double y) {
        super(x, y, "Threshold Node");
    }

    @Override
    protected void initialize() {

        input = input(Image.class, "Image", null);
        output = output(Image.class, "Image", null);

    }

    @Override
    protected void function() {
        Image i = input.getValue();

        int width = (int) i.getWidth();
		int height = (int) i.getHeight();

        PixelReader reader = i.getPixelReader();
        WritableImage out = new WritableImage(reader, width, height);
        PixelWriter writer = out.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double I = reader.getColor(x,y).getBrightness();
                if (I > 0.6) {
                    writer.setColor(x,y, new Color(1,1,1, 1));
                } else {
                    writer.setColor(x,y, new Color(0,0,0,1));
                }
            }
        }

        output.setValue(out);
    }
    
}
