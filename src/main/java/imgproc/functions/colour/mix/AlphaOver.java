package imgproc.functions.colour.mix;

import imgproc.functions.ImageMixFunction;
import imgproc.functions.ImageMixProcessor;
import javafx.scene.paint.Color;

public class AlphaOver extends ImageMixFunction {

    public AlphaOver(double factor) {
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

                Color E;
                if (M.getBrightness() > I.getBrightness()) {
                    E = M;
                } else {
                    E = I;
                }

                p.writer.setColor(x, y, E);
            }
        }
    }
    
}
