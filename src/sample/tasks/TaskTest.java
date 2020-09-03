package sample.tasks;

import java.util.Random;

public class TaskTest {


    private final static String[] task = {"src/100.png",  "src/101.png", "src/102.png", "src/102.png", "src/103.png","src/104.png",
            "src/105.png", "src/106.png","src/107.png", "src/108.png", "src/109.png", "src/110.png"};



    public static String getRandomTask(){
        Random random = new Random();
        int index = random.nextInt(task.length);
        return task[index];
    }

}
