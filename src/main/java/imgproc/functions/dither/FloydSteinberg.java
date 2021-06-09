package imgproc.functions.dither;

import imgproc.functions.ImageFunction;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FloydSteinberg implements ImageFunction {

    @Override
    public Image apply(Image in) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();

        PixelReader reader = in.getPixelReader();
        WritableImage out = new WritableImage(reader, width, height);
        PixelWriter writer = out.getPixelWriter();

        double[][] intensities = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                intensities[y][x] = reader.getColor(x, y).getBrightness();
            }
        }

        double alpha = (double) 7 / 16;
        double beta = (double) 3 / 16;
        double gamma = (double) 5 / 16;
        double sigma = (double) 1 / 16;

        double error = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (intensities[y][x] > 0.5) {
                    writer.setColor(x, y, new Color(1, 1, 1, 1));
                    error = intensities[y][x] - 1;
                } else {
                    writer.setColor(x, y, new Color(0, 0, 0, 1));
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

        return out;

    }


    @Override
    public String toString(){
        return "Floyd-Steinberg Dither";
    }
}
