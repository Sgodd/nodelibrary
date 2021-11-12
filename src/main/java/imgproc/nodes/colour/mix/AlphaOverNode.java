package imgproc.nodes.colour.mix;

import imgproc.functions.ImageMixProcessor;
import imgproc.functions.colour.mix.AlphaOver;
import imgproc.functions.colour.mix.MixLighten;
import javafx.scene.image.Image;

public class AlphaOverNode extends MixNode {

    public AlphaOverNode(double x, double y) {
        super(x, y, "Alpha Over Node");
    }

    @Override
    public void function() {
        Image image = imageInput.getValue();
        Image mask = maskInput.getValue();

        double factor = factorInput.getValue();

        ImageMixProcessor processor = new ImageMixProcessor(new AlphaOver(factor));

        if (image != null && mask != null) {
            output.setValue(processor.apply(image, mask));
        }
    }
    
}
