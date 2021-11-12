package imgproc.functions.filters;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class GaussianBlur implements ImageFunction {

    private double[][] kernel;
    private int r;

    public GaussianBlur(int radius) {
        kernel = new double[2*radius + 1][2*radius + 1];
        double sigma = (double)radius/3;
        this.r = radius;
        
        for (double y = -r; y <= r; y++) {
            for (double x = -r; x <= r; x++) {
                double val = -(x*x + y*y)/(2*sigma*sigma);
                kernel[(int)y+r][(int)x+r] = 273 * Math.exp(val);
            }
        }
    }

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width = p.width;

        int[][][] intensitiesP = new int[height][width][3];
        int[][][] intensitiesM = new int[height][width][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                intensitiesP[y][x][0] = (int) (p.reader.getColor(x,y).getRed() * 255);
                intensitiesP[y][x][1] = (int) (p.reader.getColor(x,y).getGreen() * 255);
                intensitiesP[y][x][2] = (int) (p.reader.getColor(x,y).getBlue() * 255);
                
            }
        }

        int min = 100000;
        int max = -100000;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                int[] sum = {0, 0, 0};
                
                for (int i = -r; i <= r; i++) {
                    for (int j = -r; j <= r; j++) {
                        int kx = ImageFunction.clamp(x+i, 0, width-1);
                        int ky = ImageFunction.clamp(y+j, 0, height-1);

                        sum[0] += intensitiesP[ky][kx][0] * kernel[j+r][i+r];
                        sum[1] += intensitiesP[ky][kx][1] * kernel[j+r][i+r];
                        sum[2] += intensitiesP[ky][kx][2] * kernel[j+r][i+r];

                        min = Math.min(Math.min(sum[0],sum[1]), Math.min(sum[2],min));
                        max = Math.max(Math.max(sum[0], sum[1]), Math.max(sum[2], max));
                    }                
                }

                intensitiesM[y][x][0] = sum[0];
                intensitiesM[y][x][1] = sum[1];
                intensitiesM[y][x][2] = sum[2];
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double r = ImageFunction.normalise(intensitiesM[y][x][0], min, max);
                double g = ImageFunction.normalise(intensitiesM[y][x][1], min, max);
                double b = ImageFunction.normalise(intensitiesM[y][x][2], min, max);
                p.writer.setColor(x,y, new Color(r, g, b, 1));
            }
        }

        
    }
    
}
