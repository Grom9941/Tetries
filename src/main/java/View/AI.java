package View;

import Model.Field;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AI {

    public static void main(String[] args) throws InterruptedException {
        int score=0;
        int overflow=0;
        while (overflow<20) {
            Random rand = new Random();
            Field fiel = new Field();
            BestSolver bestSolver = new BestSolver();
            List<Integer> shapeQueue = new LinkedList<Integer>();
            int[][] matrix;

            int rand1 = rand.nextInt(7) + 1;
            int rand2 = rand.nextInt(7) + 1;

            shapeQueue.add(rand1);
            shapeQueue.add(rand2);


            Pair pairBest = bestSolver.choseBest(shapeQueue);
            matrix = (int[][]) pairBest.getKey();
            if (((Double) pairBest.getValue())==Double.MAX_VALUE) break;

            fiel.insertField(matrix);
            matrixToString(matrix,score);
            fiel.heightReload();
            score+=fiel.fillFind(matrix);
            overflow = Field.maxHeight;

            TimeUnit.SECONDS.sleep(1);

            shapeQueue.remove(1);
            shapeQueue.remove(0);
        }
        System.out.println("Конец");
    }

    private static void matrixToString(int[][] field, int score){

        for (int[] aField : field) {//20
            System.out.println(Arrays.toString(aField));
        }
        System.out.println(score);
    }

}