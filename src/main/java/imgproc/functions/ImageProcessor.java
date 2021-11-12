package imgproc.functions;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageProcessor {

    private ImageFunction f;

    public int height;
    public int width;

    public PixelReader reader;
    public WritableImage out;
    public PixelWriter writer;

    public ImageProcessor(ImageFunction f) {
        this.f = f;
    }

    public Image apply(Image image) {

        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();

        reader = image.getPixelReader();
        out = new WritableImage(reader, width, height);
        writer = out.getPixelWriter();
        
        
        f.apply(this);

        return out;
    }

    public void setFunction(ImageFunction f) {
        this.f = f;
    }

    

    

}
