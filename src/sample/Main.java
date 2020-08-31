package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private static final int NUM_OF_TILE = 36;
    private static final int NUM_PER_ROW = 6;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1000, 700);

        int c = 0;
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < NUM_OF_TILE; i++) {
            tiles.add(new Tile(String.valueOf(c)));

            c++;
        }
        for (int i = 0; i < tiles.size(); i++) {

        }
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(80 * (i % NUM_PER_ROW));
            tile.setTranslateY(80 * (i / NUM_PER_ROW));
            root.getChildren().add(tile);
        }
        return root;

    }
    private class Tile extends StackPane {
        public Tile(String value) {
            Rectangle border = new Rectangle(80, 80);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            Text text = new Text(value);
            text.setFont(Font.font(30));

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
}


