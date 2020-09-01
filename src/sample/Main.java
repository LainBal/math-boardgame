package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.dice.Dice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage window;
    private static final int NUM_OF_TILE = 36;
    private static final int NUM_PER_ROW = 6;

    private int diceValue = 0;

    private int playerPosition = 0;

    private List<Tile> tiles = new ArrayList<>();

    private ImageView playerImage = new ImageView(new Image(new File("src/board_piece.png").toURI().toString()));

    private int move ( int diceValue){
        int temp = playerPosition + diceValue;
        if (temp >= NUM_OF_TILE) {
            temp = NUM_OF_TILE - 1;
        }
        Tile tile = tiles.get(temp);

        Path imagePath = new Path();
        imagePath.getElements().add(
                new MoveTo(playerImage.getTranslateX() + 10 + playerImage.getFitWidth() / 2,
                        playerImage.getTranslateY() + 10 + playerImage.getFitHeight() / 2));
        imagePath.getElements().add(
                new LineTo(tile.getTranslateX() + 10 + playerImage.getFitWidth() / 2,
                        tile.getTranslateY() + 10 + playerImage.getFitHeight() / 2));

        PathTransition imageTransition = new PathTransition();
        imageTransition.setNode(playerImage);
        imageTransition.setPath(imagePath);
        imageTransition.setDuration(Duration.seconds(1));
        imageTransition.play();

        //image.setTranslateX(tile.getTranslateX()+10);
        //image.setTranslateY(tile.getTranslateY()+10);

        return temp;

    }
    private Parent createContent() {
        Pane root = new Pane();
        //root.setPrefSize(1000, 700);

        playerImage.setTranslateX(10);
        playerImage.setTranslateY(10);

        playerImage.setFitHeight(60);
        playerImage.setFitWidth(60);

        int c = 0;
        for (int i = 0; i < NUM_OF_TILE; i++) {
            if (c == 0) {
                tiles.add(new Tile("START"));
            } else {
                tiles.add(new Tile(String.valueOf(c)));
            }
            c++;
        }
        for (int i = 0; i < tiles.size(); i++) {

        }
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(80 * (i % NUM_PER_ROW));
            tile.setTranslateY(80 * (i / NUM_PER_ROW));
            root.getChildren().addAll(tile);
        }


        Dice dice1 = new Dice();
// position of dice
        dice1.setTranslateX(150);
        dice1.setTranslateY(550);
// roll dice button
        Button btn = new Button("Roll dice");

        btn.setTranslateX(150);
        btn.setTranslateY(620);
        btn.setOnAction(event -> {
            diceValue = (int) (Math.random() * (Dice.MAX_VALUE - Dice.MIN_VALUE + 1)) + Dice.MIN_VALUE;
            dice1.rollExact(diceValue);
            playerPosition = move(diceValue);
            if (playerPosition == NUM_OF_TILE - 1) {
//                endGame;
            }

        });

        root.getChildren().addAll(dice1, btn, playerImage);
        return root;

    }

    private class Tile extends StackPane {
        public Tile(String value) {
            Rectangle border = new Rectangle(80, 80);
            border.setFill(Color.RED);
            border.setStroke(Color.BLACK);



            Text text = new Text(value);
            text.setFont(Font.font(24));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        VBox vBox = new VBox();

        Label label = new Label("Welcome new player!");
        label.setFont(Font.font("Roboto", FontWeight.BOLD, FontPosture.ITALIC, 30));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.BLUE);
        label.setPadding(new Insets(100, 50, 50, 50));


        Label label1 = new Label("Are you ready? Roll the dice and we will see what  are you made of....");
        label1.setMaxWidth(400);
        label1.setWrapText(true);
        label1.setFont(Font.font("Roboto", 25));
        label1.setPadding(new Insets(10, 50, 200, 50));

        vBox.getChildren().addAll(label, label1);


        HBox hBox = new HBox();
        Label label2 = new Label("Score: ");
        label2.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        label2.setPadding(new Insets(10, 50, 50, 670));

        Label label3 = new Label("100% ");
        label3.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        label3.setPadding(new Insets(10, 10, 50, 10));

        hBox.getChildren().addAll(label2, label3);


        BorderPane borderPane = new BorderPane();
        borderPane.setRight(vBox);
        borderPane.setBottom(hBox);
        borderPane.setLeft(createContent());

        window = primaryStage;
        Scene scene = new Scene(borderPane, 1000, 700);
        window.setScene(scene);
        window.setTitle("Game");
        window.show();
    }
    private void endGame () {

        //jauzraksta metode kas notiek pie pedeja laucina(35) ( piem, atbild uz jautajum un izlec logs "thank you for
        //playing vai kaut ka ta

    }
}

