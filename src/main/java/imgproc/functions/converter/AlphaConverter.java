package imgproc.functions.converter;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class AlphaConverter implements ImageFunction {

    private double threshold;
    private double factor;

    public AlphaConverter(double threshold, double factor) {
        this.threshold = threshold;
        this.factor = factor;
    }

    @Override
    public void apply(ImageProcessor p) {
        int width  = p.width;
        int height = p.height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Color pixel = p.reader.getColor(x, y);

                if (pixel.getBrightness() < threshold) {
                    Color out = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), 1-factor);
                    p.writer.setColor(x, y, out);
                }

            }
        }                   
    }
    
}
