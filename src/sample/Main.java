package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.dice.Dice;
import sample.tasks.TaskTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private ImageView playerImage = new ImageView(new Image(new File("src/red2.png").toURI().toString()));


    private Parent createContent() throws FileNotFoundException {
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
            } else { if (c==35)
            { tiles.add(new Tile("FINIŠS"));} else{
                tiles.add(new Tile(String.valueOf(c)));}
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
        public Tile(String value) throws FileNotFoundException {
            Rectangle border = new Rectangle(80, 80);
            Image img = new Image(new FileInputStream("src/116_1.png"));

            border.setFill(Color.LIGHTBLUE);
            border.setFill(new ImagePattern(img));

            border.setStroke(Color.BLACK);
            Text text = new Text(value);
            text.setFont(Font.font(24));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


//Texta vizuaja daļa
        VBox vBox = new VBox();

        Label label = new Label("Welcome new player!");
        label.setFont(Font.font("Roboto", FontWeight.BOLD, FontPosture.ITALIC, 30));
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.DARKBLUE);
        label.setPadding(new Insets(100, 50, 50, 50));


        Label label1 = new Label("Are you ready? Roll the dice and we will see what you are made of....");
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

    private int move(int diceValue) {
        int temp = playerPosition + diceValue;
        if (temp >= NUM_OF_TILE) {
            temp = NUM_OF_TILE - 1;
            if(temp == NUM_OF_TILE - 1){

                try { finalWindow();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
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

        //šis event atver jaunu logu, kad beidzas animacija
        imageTransition.setOnFinished(event -> {
            try {
                newWindow();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        imageTransition.play();

        //image.setTranslateX(tile.getTranslateX()+10);
        //image.setTranslateY(tile.getTranslateY()+10);

        return temp;

    }
//new window metode
    private void newWindow() throws FileNotFoundException {

        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 600, 600);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(30);

        Image image = new Image(new FileInputStream(TaskTest.getRandomTask()));
        ImageView imageView = new ImageView(image);

        imageView.setX(50);
        imageView.setY(25);
        imageView.maxHeight(300);
        imageView.maxWidth(300);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Question!");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(window);

        // Set position of second window, related to primary window.
        newWindow.setX(window.getX() + 200);
        newWindow.setY(window.getY() + 100);

        Button button100 = new Button("Forwards to the next task");
        //button100.setOnAction(e -> window.setScene(scene));
        button100.setAlignment(Pos.CENTER);
        button100.setMaxSize(300, 10);
        button100.setStyle("-fx-background-color: #9e0000 ; " + "-fx-text-fill: #ffffff;" + "-fx-font-size: 1.5em;");
        button100.setPadding(new Insets(10, 10, 10, 60));
        button100.setOnAction(actionEvent -> {
                    newWindow.close();
                }

        );


        secondaryLayout.getChildren().addAll(vbox, imageView, button100);
        vbox.getChildren().addAll(imageView, button100);
        vbox.setAlignment(Pos.CENTER);
        newWindow.show();
    }

    private void endGame() {
        window.close();

        //jauzraksta metode kas notiek pie pedeja laucina(35) ( piem, atbild uz jautajum un izlec logs "thank you for
        //playing vai kaut ka ta

    }
    private void finalWindow() throws FileNotFoundException {

        StackPane secondaryLayout2 = new StackPane();
        Scene secondScene2 = new Scene(secondaryLayout2, 600, 600);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(30);

        Image image2 = new Image(new FileInputStream(TaskTest.getRandomTask()));
        ImageView imageView2 = new ImageView(image2);

        imageView2.setX(50);
        imageView2.setY(25);
        imageView2.maxHeight(300);
        imageView2.maxWidth(300);

        //Setting the preserve ratio of the image view
        imageView2.setPreserveRatio(true);

        // New window (Stage)
        Stage finalWindow = new Stage();
        finalWindow.setTitle("Question!");
        finalWindow.setScene(secondScene2);

        // Specifies the modality for new window.
        finalWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        finalWindow.initOwner(window);

        // Set position of second window, related to primary window.
        finalWindow.setX(window.getX() + 200);
        finalWindow.setY(window.getY() + 100);

        Button button1001 = new Button("END GAME");
        //button100.setOnAction(e -> window.setScene(scene));
        button1001.setAlignment(Pos.CENTER);
        button1001.setMaxSize(300, 10);
        button1001.setStyle("-fx-background-color: #9e0000 ; " + "-fx-text-fill: #ffffff;" + "-fx-font-size: 1.5em;");
        button1001.setPadding(new Insets(10, 10, 10, 60));
        button1001.setOnAction(actionEvent -> {
                    finalWindow.close();
                    endGame();
                }

        );


        secondaryLayout2.getChildren().addAll(vbox, imageView2, button1001);
        vbox.getChildren().addAll(imageView2, button1001);
        vbox.setAlignment(Pos.CENTER);
        finalWindow.show();
    }
}

