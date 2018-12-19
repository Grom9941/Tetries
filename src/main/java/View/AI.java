package View;

import Model.Field;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AI extends Field {

    public static void main(String[] args) throws InterruptedException {

        int score = 0;
        int overflow = 0;

        while (overflow < 20) {

            Random random = new Random();
            BestSolver bestSolver = new BestSolver();
            List<Integer> shapeQueue = new LinkedList<Integer>();
            int[][] matrix;

            //формируем массив из двух рандомных фигур
            int rand1 = random.nextInt(7) + 1;
            int rand2 = random.nextInt(7) + 1;

            shapeQueue.add(rand1);
            shapeQueue.add(rand2);

            //лучшее решение из двух фигур
            Pair pairBest = bestSolver.choseBest(shapeQueue);
            matrix = (int[][]) pairBest.getKey();

            //переполнение
            if (((Double) pairBest.getValue()) == Double.MAX_VALUE) break;

            //обновляет состояние поля
            insertField(matrix);
            matrixToString(matrix, score);
            heightReload();
            score += fillFind(matrix);
            overflow = maxHeight;

            TimeUnit.SECONDS.sleep(1);

            shapeQueue.remove(1);
            shapeQueue.remove(0);
        }
        System.out.println("Конец");
    }

    private static void matrixToString(int[][] field, int score) {

        for (int[] aField : field) {
            System.out.println(Arrays.toString(aField));
        }
        System.out.println(score);
    }

}
