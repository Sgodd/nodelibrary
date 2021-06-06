package nodelibrary.editor.node.components.control;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;

public abstract class DataInput<T> extends Group {
    
    protected DataControl<T> dataControl;
    protected SimpleObjectProperty<T> valueProperty = new SimpleObjectProperty<>();

    public DataInput(DataControl<T> control) {

        this.dataControl = control;

        valueProperty.addListener(new ChangeListener<T>(){
            @Override
            public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                System.out.println("Data Changed");           
            }
        });
    }
}
