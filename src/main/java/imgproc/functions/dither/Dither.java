package imgproc.functions.dither;

import imgproc.functions.ImageFunction;
import imgproc.functions.ImageProcessor;
import javafx.scene.paint.Color;

public class Dither {
    public final ImageFunction[] functions = {
        new FloydSteinberg2()
    };
}

class FloydSteinberg2 implements ImageFunction {

    private static final double alpha = (double) 7 / 16;
    private static final double beta = (double) 3 / 16;
    private static final double gamma = (double) 5 / 16;
    private static final double sigma = (double) 1 / 16;

    @Override
    public void apply(ImageProcessor p) {
        int height = p.height;
        int width = p.width;

        double[][] intensities = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                intensities[y][x] = p.reader.getColor(x, y).getBrightness();
            }
        }

        double error = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (intensities[y][x] > 0.5) {
                    p.writer.setColor(x, y, new Color(1, 1, 1, 1));
                    error = intensities[y][x] - 1;
                } else {
                    p.writer.setColor(x, y, new Color(0, 0, 0, 1));
                    error = intensities[y][x];
                }

                if (x < width - 1)
                    intensities[y][x + 1] += error * alpha;
                if (y < height - 1 && x > 0)
                    intensities[y + 1][x - 1] += error * beta;
                if (y < height - 1)
                    intensities[y + 1][x] += error * gamma;
                if (y < height - 1 && x < width - 1)
                    intensities[y + 1][x + 1] += error * sigma;
            }
        }
    }

    @Override
    public String toString() {
        return "Floyd-Steinberg Dither";
    }
}

