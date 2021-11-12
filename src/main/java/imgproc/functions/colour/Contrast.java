package imgproc.functions.colour;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class Contrast implements ImageFunction {
    private double value = 0;
    private double factor = 0;

    public Contrast(double value) {
        this.value = value;        
        this.factor = ((259/255) * (value + 1.0)) / (1.0 * (259/255 - value));
    }

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width  = p.width;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = p.reader.getColor(x, y);

                double r = correction(pixel.getRed());
                double g = correction(pixel.getGreen());
                double b = correction(pixel.getBlue());

                p.writer.setColor(x, y, new Color(r, g, b, 1));
            }
        }    
    }

    private double correction(double value) {
        return ImageFunction.clamp((factor * (value - 0.5)) + 0.5, 0, 1);
    }

}
