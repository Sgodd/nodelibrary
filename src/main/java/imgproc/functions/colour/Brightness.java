package imgproc.functions.colour;


import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class Brightness implements ImageFunction {

    private double value = 0;

    public Brightness(double value) {
        this.value = value;        
    }

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width  = p.width;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = p.reader.getColor(x, y);
                double r = ImageFunction.clamp(pixel.getRed() + value, 0, 1);
                double g = ImageFunction.clamp(pixel.getGreen() + value, 0, 1);
                double b = ImageFunction.clamp(pixel.getBlue() + value, 0, 1);

                p.writer.setColor(x, y, new Color(r, g, b, 1));
            }
        }    
    }
    
}
