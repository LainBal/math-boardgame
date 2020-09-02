package sample.uzd;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;

public class Uzd100 extends Application{
    Stage window;
    Scene scene;

    @Override
    public void start(Stage stage) throws  Exception{
            window = stage;
            stage.setTitle("100.uzdevums");


        VBox vbox = new VBox();

        FileInputStream input = new FileInputStream("src/100.png");
        Image image = new Image(input);
        ImageView iv2 = new ImageView(image);


        iv2.setImage(image);
        //iv2.
        iv2.setFitWidth(500);
        iv2.setFitHeight(400);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);


        Button button100 = new Button("Forwards to the next task");
        //button100.setOnAction(e -> window.setScene(scene));
        button100.setAlignment(Pos.CENTER);
        button100.setMaxSize(300, 10);
        button100.setStyle("-fx-background-color: #9e0000 ; " + "-fx-text-fill: #ffffff;" + "-fx-font-size: 1.5em;");
       // button100.setPadding(new Insets(10, 10, 10, 10));

        vbox.getChildren().addAll(iv2, button100);
        vbox.setAlignment(Pos.CENTER);



//        StackPane layout2 = new StackPane();
//        layout2.getChildren().add(button2);
            scene = new Scene(vbox,500, 500 );


            window.setScene(scene);
            window.show();




    }
    public static void main(String[] args){
        launch(args);
    }

}