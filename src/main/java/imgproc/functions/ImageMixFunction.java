package imgproc.functions;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class ImageMixFunction {

    protected double factor = 0;

    public ImageMixFunction(double factor) {
        this.factor = factor;
    }

    public abstract void apply(ImageMixProcessor p);

    public static Color blend(Color bg, Color fg, double alpha) {

        double bgr = bg.getRed();
        double bgg = bg.getGreen();
        double bgb = bg.getBlue();
        double bga = bg.getOpacity();

        double fgr = fg.getRed();
        double fgg = fg.getGreen();
        double fgb = fg.getBlue();
        double fga = alpha * fg.getOpacity();


        double a = fga + bga * (1-fga);

        double r = (fga*fgr + (bga*bgr) * (1 - fga))/a;
        double g = (fga*fgg + (bga*bgg) * (1 - fga))/a;
        double b = (fga*fgb + (bga*bgb) * (1 - fga))/a;

        // double r = ImageFunction.clamp(fga*fgr + (bga*bgr) * (1 - fga) , 0, 1);
        
        // double r = ImageFunction.clamp((bga * bgr) + (alpha - fga) * fgr, 0, 1);
        // double g = ImageFunction.clamp((bga * bgg) + (alpha - fga) * fgg, 0, 1);
        // double b = ImageFunction.clamp((bga * bgb) + (alpha - fga) * fgb, 0, 1);
        // double a = ImageFunction.clamp(bga + fga, 0, 1);

        return new Color(r, g, b, a);
    }
    
}
