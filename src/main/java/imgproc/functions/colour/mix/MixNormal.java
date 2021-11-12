package imgproc.functions.colour.mix;

import imgproc.functions.ImageMixFunction;
import imgproc.functions.ImageMixProcessor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MixNormal extends ImageMixFunction {


    public MixNormal(double factor) {
        super(factor);
    }

    @Override
    public void apply(ImageMixProcessor p) {
        int width  = p.width;
        int height = p.height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Color M = p.mReader.getColor(x, y);
                Color I = p.iReader.getColor(x,y);

                Color E = ImageMixFunction.blend(I, M, factor);

                p.writer.setColor(x, y, E);
            }
        }    
    }
    
}
