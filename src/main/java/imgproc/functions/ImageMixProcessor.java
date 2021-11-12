package imgproc.functions;

import javax.swing.GroupLayout.Alignment;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageMixProcessor {

    private ImageMixFunction f;

    public int mHeight;
    public int mWidth;

    public int height;
    public int width;

    public PixelReader mReader;
    public PixelReader iReader;
    public WritableImage out;
    public PixelWriter writer;

    public ImageMixProcessor(ImageMixFunction f) {
        this.f = f;
    }

    public Image apply(Image mask, Image image) {

        mHeight = (int) mask.getHeight();
        mWidth  = (int) mask.getWidth();
        
        height = (int) image.getHeight();
        width  = (int) image.getWidth();

        mReader = mask.getPixelReader();
        iReader = image.getPixelReader();

        out = new WritableImage(width, height);
        writer = out.getPixelWriter();

        f.apply(this);

        return out;
    }

    public void setFunction(ImageMixFunction f) {
        this.f = f;
    }

}
