package imgproc.functions.colour.mix;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageMixFunction;
import imgproc.functions.ImageMixProcessor;
import javafx.scene.paint.Color;

public class MixMultiply extends ImageMixFunction {

    public MixMultiply() {
        super(0);
    }

    public MixMultiply(double factor) {
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

                double r = ImageFunction.clamp(factor*M.getOpacity()*M.getRed() + I.getOpacity()*I.getRed(), 0, 1);
                double g = ImageFunction.clamp(factor*M.getOpacity()*M.getGreen() + I.getOpacity()*I.getGreen(), 0, 1);
                double b = ImageFunction.clamp(factor*M.getOpacity()*M.getBlue() + I.getOpacity()*I.getBlue(), 0, 1);
                double a = ImageFunction.clamp(factor*M.getOpacity() + I.getOpacity(), 0, 1);

                p.writer.setColor(x, y, new Color(r, g, b, a));
            }
        }    
    }
    
    
}