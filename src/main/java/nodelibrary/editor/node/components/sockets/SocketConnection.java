package nodelibrary.editor.node.components.sockets;

import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import nodelibrary.editor.node.events.SocketEvent;

public class SocketConnection<T> extends CubicCurve {
    
    private SocketOutput<T> out;
    private SocketInput<T> in;


    public SocketConnection() {
        init();
        setVisible(false);
        setStroke(Color.BLACK);
    }

    public SocketConnection(SocketOutput<T> out, SocketInput<T> in) {
        this.out = out;
        this.in = in;

        setStart(out.getCenter());
        setEnd(in.getCenter());        

        SocketController.MAIN.getChildren().add(this);

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

    public void passValue() {
        in.getSection().setValue(out.getSection().getValue());
    }

    public void assign() {
        in.setConnection(this);
        out.addConnection(this);
    }

    public void destroy() {

        in.disown(this);
        out.disown(this);
        SocketController.MAIN.getChildren().remove(this);

        // out.removeConnection(this);
        // in.removeConnection(this);        

        // out.fireEvent(new SocketEvent(SocketEvent.OUTPUT_UNLINKED, this));
        // in.fireEvent(new SocketEvent(SocketEvent.INPUT_UNLINKED, this));

    }
}
