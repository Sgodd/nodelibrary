package nodelibrary.editor.node.components.sockets;

import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public class SocketConnection extends CubicCurve {
    
    SocketOutput<?> out;
    SocketInput<?> in;


    public SocketConnection() {
        init();
        setVisible(false);
        setStroke(Color.BLACK);
    }

    public SocketConnection(SocketOutput<?> out, SocketInput<?> in) {

        this.out = out;
        this.in = in;

        setStart(out.getCenter());
        setEnd(in.getCenter());        

        init();
    }

    public void init() {
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        
        controlX1Property().bind(
            Bindings.createObjectBinding(()->{
                return lerp(getStartX(), getEndX(), 0.4);
            }, startXProperty(), endXProperty())
        );

        controlX2Property().bind(
            Bindings.createObjectBinding(() -> {
                return lerp(getEndX(), getStartX(), 0.4);

            }, startXProperty(), endXProperty())
        );

        controlY1Property().bind(
            Bindings.createObjectBinding(() ->{
                return lerp(getStartY(), getEndY(), 0.1);
            }, startYProperty(), endYProperty())
        );

        controlY2Property().bind(
            Bindings.createObjectBinding(()->{
                return lerp(getStartY(), getEndY(), 0.9);
            }, startYProperty(), endYProperty())
        );
    }

    public void setStart(Point2D start) {
        setStartX(start.getX());
        setStartY(start.getY());
    }

    public void setEnd(Point2D end) {
        setEndX(end.getX());
        setEndY(end.getY());
    }

    public static double lerp(double start, double end, double ratio) {
        return start + (end - start) * ratio;
    }
}
