import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AI {

    public static void main(String[] args) {
        while (true) {
            Random rand = new Random();
            BestSolver bestSolver = new BestSolver();
            List<Integer> shapeQueue = new LinkedList<Integer>();
            int[][] matrix= new int[20][20];

            int rand1 = rand.nextInt(7) + 1;
            int rand2 = rand.nextInt(7) + 1;

            shapeQueue.add(rand1);
            shapeQueue.add(rand2);

            matrix=bestSolver.choseBest(shapeQueue);
            System.out.println(matrix);

            shapeQueue.remove(1);
            shapeQueue.remove(0);
        }


    }

}
