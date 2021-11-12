package imgproc.nodes.colour.mix;

import imgproc.functions.ImageMixProcessor;
import imgproc.functions.colour.mix.MixMultiply;
import javafx.scene.image.Image;

public class MixMultiplyNode extends MixNode {

    public MixMultiplyNode(double x, double y) {
        super(x, y, "Mix Lighten Node");
    }

    @Override
    public void function() {
        Image image = imageInput.getValue();
        Image mask = maskInput.getValue();

        double factor = factorInput.getValue();

        ImageMixProcessor processor = new ImageMixProcessor(new MixMultiply(factor));

        if (image != null && mask != null) {
            output.setValue(processor.apply(image, mask));
        }
    }
}