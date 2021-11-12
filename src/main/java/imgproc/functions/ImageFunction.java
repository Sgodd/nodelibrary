package imgproc.functions;

public interface ImageFunction {
    public void apply(ImageProcessor p);

    public static double clamp(double value, double min, double max) {
        return value > max ? max : value < min ? min : value;
    }

    public static int clamp(int value, int min, int max) {
        return value > max ? max : value < min ? min : value;
    }

    public static double normalise(double value, double min, double max) {
		return (value - min)/(max-min);
	}
}

