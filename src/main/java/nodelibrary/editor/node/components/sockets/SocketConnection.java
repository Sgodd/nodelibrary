package nodelibrary.editor.node.components.sockets;

import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public class SocketConnection extends CubicCurve {
    
    SocketOutput<?> from;
    SocketInput<?> to;


    public SocketConnection() {
        init();
        setVisible(false);
        setStroke(Color.BLACK);
    }

    public SocketConnection(SocketOutput<?> out, SocketInput<?> in) {
        init();
    }

    public void init() {
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(2);

        setStartX(700);
        setStartY(500);
        setEndX(300);
        setEndY(300);
        
        controlX1Property().bind(
            Bindings.createObjectBinding(()->{
                return lerp(getStartX(), getEndX(), 0.4);
                // return getStartX() + Math.abs(getEndX() - getStartX()) * 0.4;
                // return getStartX() + 20;
            }, startXProperty(), endXProperty())
        );

        controlX2Property().bind(
            Bindings.createObjectBinding(() -> {
                return lerp(getEndX(), getStartX(), 0.4);
                // return getEndX() - Math.abs(getStartX() - getEndX()) * 0.4;
                // return getStartX() + Math.abs(getEndX() - getStartX()) * 0.6;

            }, startXProperty(), endXProperty())
        );

        controlY1Property().bind(
            Bindings.createObjectBinding(() ->{
                return lerp(getStartY(), getEndY(), 0.1);
                // return getStartY() + (getEndY() - getStartY()) * 0.1;
            }, startYProperty(), endYProperty())
        );

        controlY2Property().bind(
            Bindings.createObjectBinding(()->{
                return lerp(getStartY(), getEndY(), 0.9);
                // return getStartY() + (getEndY() - getStartY()) * 0.9;
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
