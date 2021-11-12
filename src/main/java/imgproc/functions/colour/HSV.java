package imgproc.functions.colour;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class HSV implements ImageFunction {

    private double hue = 0;
    private double sat = 0;
    private double bri = 0;

    public HSV(double hue, double sat, double bri) {
        this.hue = hue;
        this.sat = sat;
        this.bri = bri;       
    }

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width  = p.width;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = p.reader.getColor(x, y);

                double h = (pixel.getHue() + this.hue) % 360;
                double s = ImageFunction.clamp(pixel.getSaturation() + sat, 0, 1);
                double b = ImageFunction.clamp(pixel.getBrightness() + bri, 0, 1);

                Color out = Color.hsb(h, s, b);

                p.writer.setColor(x, y, out);
            }
        }    
    }
    
}
