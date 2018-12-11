import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AI {

    public static void main(String[] args) {
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

            matrix=bestSolver.choseBest(shapeQueue);

            fiel.insertField(matrix);
            score+=fiel.fillFind(matrix);
            fiel.heightReload();
            overflow = fiel.maxHeight;

            System.out.println(Arrays.deepToString(matrix));
            System.out.println(score);

            shapeQueue.remove(1);
            shapeQueue.remove(0);
        }


    }

}
