package imgproc.functions.dither;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;

public class OrderedDither implements ImageFunction {

    private int[][]  template;

    @Override
    public String toString() {
        return "Ordered Dither";
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
