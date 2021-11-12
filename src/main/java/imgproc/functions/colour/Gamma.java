package imgproc.functions.colour;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class Gamma implements ImageFunction {

    private double gamma = 1;
    public Gamma(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width  = p.width;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = p.reader.getColor(x,y);

                double i = pixel.getBrightness();

                
                double v = ImageFunction.clamp(Math.pow(i/pixel.getOpacity(), 1/gamma), 0, 1);


                Color c = Color.hsb(pixel.getHue(), pixel.getSaturation(), v);

                p.writer.setColor(x, y, c);
            }
        }            
    }
    
}
