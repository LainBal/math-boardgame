package sample.tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class TaskTest {

    static int index = 0;

    public static List<String> task = Arrays.asList("src/100.png", "src/101.png", "src/102.png", "src/102.png", "src/103.png", "src/104.png",
            "src/105.png", "src/106.png", "src/107.png", "src/108.png", "src/109.jpg", "src/110.png");


    public static void shuffle() {
        Collections.shuffle(task);
    }

    public static String getRandomTask() {

        if (++index == task.size()) {
            index = 0;
            Collections.shuffle(task);
        }
        return task.get(index);
    }
}
