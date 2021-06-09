package imgproc.nodes.components;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import nodelibrary.editor.node.Node;
import nodelibrary.editor.node.components.NodeSection;

public class ImageSection extends NodeSection {

    public final ImageView view = new ImageView();

    private boolean resizing = false;
    private boolean panning = false;

    

    private Rectangle2D viewport;

    private double width;
    private double height;

    private Point2D imagePos;

    private static final double MIN_WIDTH = 120;
    private static final double MAX_WIDTH = 720;
    private static final double MIN_HEIGHT = 50;
    private static final double MAX_HEIGHT = 720;

    private static final double MIN_ZOOM_WIDTH = 10;

    public ImageSection(Node parent) {
        super(parent);
        
        
        HBox hbox = new HBox();
        hbox.getChildren().add(view);

        grid.add(hbox, 0, 0);


        initHandlers();
    }

    public void initHandlers() {

        view.imageProperty().addListener((observable, oldValue, newValue) -> {

            initImage(newValue);

        });

        view.setOnMouseMoved(e -> {
            if (inResizeableArea(e) || resizing) {
                getScene().setCursor(Cursor.SE_RESIZE);
            } else if (panning || !resizing) {
                getScene().setCursor(Cursor.MOVE);
            }

            e.consume();
        });

        view.setOnMousePressed(e -> {

            imagePos = viewToImage(e.getX(), e.getY());

            if (inResizeableArea(e)) {
                panning = false;
                resizing = true;
            } else {
                resizing = false;
                panning = true;
            }

        });

        view.setOnScroll(e -> {
            zoom(e);
        });

        view.setOnMouseDragged(e -> {
            if (panning) {
                pan(e);

            } else if (resizing) {
                resize(e);
            }

            e.consume();
        });

        view.setOnMouseExited(e -> {
            if (!panning && !resizing) {
                getScene().setCursor(Cursor.DEFAULT);
            }

            e.consume();
        });

        view.setOnMouseReleased(e -> {
            getScene().setCursor(Cursor.DEFAULT);
            panning = false;
            resizing = false;

            e.consume();
        });
    }

    private void resizeView(double width, double height) {
        width = clamp(width, MIN_WIDTH, MAX_WIDTH);
        height = clamp(height, MIN_HEIGHT, MAX_HEIGHT);

        view.setFitWidth(width);
        view.setFitHeight(height);
    }

    private boolean inResizeableArea(MouseEvent e) {
        return e.getX() > view.getFitWidth() - 10 && e.getY() > view.getFitHeight() - 10;
    }

    private void resize(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (e.isShiftDown()) {
            double a = view.getFitHeight() / view.getFitWidth();
            double b = -1 / a;
            double c = y - (b * x);

            double minX = c / (a-b);
            double minY = minX * a;

            resizeView(minX, minY);
            
        } else {
            resizeView(e.getX(), e.getY());
        }
    }

    private void zoom(ScrollEvent e) {
        double delta = e.getDeltaY();
        double scale = Math.pow(1.001, delta);

        double newWidth = viewport.getWidth() / scale;
        double newHeight = viewport.getHeight() / scale;

        newWidth = clamp(newWidth, MIN_ZOOM_WIDTH, width);
        newHeight = clamp(newHeight, MIN_ZOOM_WIDTH * height/width, height);

        Point2D i = viewToImage(e.getX(), e.getY());

        double vx = e.getX() / view.getBoundsInLocal().getWidth();
        double vy = e.getY() / view.getBoundsInLocal().getHeight();

        double minX = i.getX() - (vx * newWidth);
        double minY = i.getY() - (vy * newHeight);

        minX = clamp(minX, 0, width-newWidth);
        minY = clamp(minY, 0, height-newHeight);

        viewport = new Rectangle2D(minX, minY, newWidth, newHeight);
        view.setViewport(viewport);
        
    }

    private void pan(MouseEvent e) {
        double width = viewport.getWidth();
        double height = viewport.getHeight();

        // Current position of mouse over image
        Point2D m = viewToImage(e.getX(), e.getY());

        // Difference in positions of the image and the new mouse position
        Point2D d = m.subtract(imagePos);

        double xMin = viewport.getMinX() - d.getX();
        double yMin = viewport.getMinY() - d.getY();
        
        double xMax = this.width - width;
        double yMax = this.height - height;

        double x = clamp(xMin, 0, xMax);
        double y = clamp(yMin, 0, yMax);

        viewport = new Rectangle2D(x, y, width, height);
        view.setViewport(viewport);
    }

    private double clamp(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }


    private Point2D viewToImage(double x, double y) {
        double xRatio = x / view.getBoundsInLocal().getWidth();
        double yRatio = y / view.getBoundsInLocal().getHeight();

        double ix = xRatio * viewport.getWidth() + viewport.getMinX();
        double iy = yRatio * viewport.getHeight() + viewport.getMinY();
        
        return new Point2D(ix, iy);
    }

    private void initImage(Image image) {
    
        width = image.getWidth();
        height = image.getHeight();

        double ratio = height / width;

        if (ratio > 1) {
            if (height > MAX_HEIGHT) {
                resizeView(MAX_HEIGHT/ratio, MAX_HEIGHT);
            } else {
                resizeView(width, height);
            }
        } else {
            if (width > MAX_WIDTH) {
                resizeView(MAX_WIDTH, MAX_WIDTH*ratio);
            } else {
                resizeView(width,height);
            }
        }

        viewport = new Rectangle2D(0, 0, width, height);
        view.setViewport(viewport);
    }

}
