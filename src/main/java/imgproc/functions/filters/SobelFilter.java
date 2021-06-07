package imgproc.functions.filters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SobelFilter {
    
    private static double[][] xKernel = {
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}
    };

    private static double[][] yKernel = {
        {1, 2, 1},
        {0,0,0},
        {-1, -2, -1}
    };

    public Image apply(Image in) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();

		PixelReader reader = in.getPixelReader();
		WritableImage out = new WritableImage(reader, width, height);
		PixelWriter writer = out.getPixelWriter();

        double[][] img = new double[height][width];

        double[][] G  = new double[height][width];
        double[][] Gx = new double[height][width];
        double[][] Gy = new double[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img[y][x] = reader.getColor(x,y).getBrightness();
            }
        }

        double max = -1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {



                int kxMin = -Math.min(1, x);
                int kxMax = Math.min(width-1-x, 1);

                int kyMin = -Math.min(1, y);
                int kyMax = Math.min(height-1-y, 1);

                for (int ky = kyMin; ky <= kyMax; ky++) {
                    for (int kx = kxMin; kx <= kxMax; kx++) {
                        
                        // System.out.println(x + " " + y);
                        Gx[y][x] += img[y+ky][x+kx] * xKernel[ky+1][kx+1];
                        Gy[y][x] += img[y+ky][x+kx] * yKernel[ky+1][kx+1];
                    }
                }

                double g = Math.sqrt(Math.pow(Gx[y][x],2) + Math.pow(Gy[y][x],2));

                G[y][x] = g;

                max = g > max ? g : max;
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double i = (1/max) * G[y][x];
                writer.setColor(x, y, new Color(i,i,i,1));
            }
        }

		return out;

	}
}
