package sample.tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class TaskTest {

    static int index = 0;

    public static List<String> task = Arrays.asList("src/images/100.png","src/images/100.png", "src/images/102.png",
            "src/images/102.png", "src/images/103.png", "src/images/104.png", "src/images/105.png",
            "src/images/106.png", "src/images/107.png", "src/images/108.png", "src/images/109.jpg", "src/images/110.png",
            "src/images/1.png", "src/images/2.png", "src/images/3.png", "src/images/4.png", "src/images/5.png",
            "src/images/6.png", "src/images/7.png", "src/images/8.png", "src/images/9.png", "src/images/10.png",
            "src/images/11.png", "src/images/12.png", "src/images/13.png");


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
