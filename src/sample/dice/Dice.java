package sample.dice;

import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Dice extends StackPane { // use when stacking different objects on to other

    public static final int MAX_VALUE = 6;
    public static final int MIN_VALUE = 1;

    public final SimpleIntegerProperty valueProperty = new SimpleIntegerProperty();

    public Dice() {

        Rectangle rect = new Rectangle(60,60);

        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(5);

        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(1);
        text.textProperty().bind(valueProperty.asString());

        this.setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);

        this.setOnMouseClicked(event -> rollExact(1));
    }

    // method for rotate roll animation & set exact dice value
    public void rollExact(int value){
        RotateTransition rt = new RotateTransition(Duration.seconds(1), this);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setOnFinished(event -> {
            valueProperty.set(value);
        });

        rt.play();
    }
}
