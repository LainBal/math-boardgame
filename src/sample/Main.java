package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.dice.Dice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private static final int NUM_OF_TILE = 36;
    private static final int NUM_PER_ROW = 6;

    private int diceValue = 0;

    private int playerPosition = 0;

    private List<Tile> tiles = new ArrayList<>();

    private ImageView playerImage = new ImageView(new Image(new File("src/board_piece.png").toURI().toString()));

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1000, 700);

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
            border.setFill(null);
            border.setStroke(Color.BLACK);

            Text text = new Text(value);
            text.setFont(Font.font(24));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private int move(int diceValue) {
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

    private void endGame() {

        //jauzraksta metode kas notiek pie pedeja laucina(35) ( piem, atbild uz jautajum un izlec logs "thank you for
        //playing vai kaut ka ta

    }
}


