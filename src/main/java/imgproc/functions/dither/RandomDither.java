package imgproc.functions.dither;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class RandomDither implements ImageFunction {

    @Override 
    public String toString() {
        return "Random Dither";
    }

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width = p.width;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {


            }
        }
    }
    
}

