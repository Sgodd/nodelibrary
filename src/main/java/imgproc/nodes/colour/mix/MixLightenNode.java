package imgproc.nodes.colour.mix;

import imgproc.functions.ImageMixProcessor;
import imgproc.functions.colour.mix.MixLighten;
import javafx.scene.image.Image;

public class MixLightenNode extends MixNode {

    public MixLightenNode(double x, double y) {
        super(x, y, "Mix Lighten Node");
    }

    @Override
    public void function() {
        Image image = imageInput.getValue();
        Image mask = maskInput.getValue();

        double factor = factorInput.getValue();

        ImageMixProcessor processor = new ImageMixProcessor(new MixLighten(factor));

        if (image != null && mask != null) {
            output.setValue(processor.apply(image, mask));
        }
    }
}