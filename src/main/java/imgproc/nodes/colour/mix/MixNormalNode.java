package imgproc.nodes.colour.mix;

import imgproc.functions.ImageMixProcessor;
import imgproc.functions.colour.mix.MixNormal;
import javafx.scene.image.Image;

public class MixNormalNode extends MixNode {
    public MixNormalNode(double x, double y) {
        super(x, y, "Mix Normal Node");
    }

    @Override
    public void function() {
        Image image = imageInput.getValue();
        Image mask = maskInput.getValue();

        double factor = factorInput.getValue();

        ImageMixProcessor processor = new ImageMixProcessor(new MixNormal(factor));

        if (image != null && mask != null) {
            output.setValue(processor.apply(image, mask));
        }
    }
}
