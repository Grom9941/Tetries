package View;

import Model.Field;
import javafx.util.Pair;

import java.util.*;

public class BestSolver extends AllCombinationsReturn {

    //Хотел использовать TreeMap для сортировки сразу но изза одинаковых значений функции это не возможно
    private Map<int[][], Double> mapBestFigure2 = new HashMap<int[][], Double>();

    public Pair<int[][], Double> choseBest(List<Integer> shapeQueue) {

        int[][] result = new int[0][];
        int[][] matrix = Field.field;
        Double min = Double.MAX_VALUE;
        // все комбинации с первой фигурой
        Map<int[][], Double> mapAllCombinationsFigure1 = allPeharbsSituation(shapeQueue, matrix, 0);

        //все комбинации с первой и второй фигурой
        for (int i = 0; i < mapAllCombinationsFigure1.size(); i++) {
            mapBestFigure2.putAll(allPeharbsSituation(shapeQueue, (int[][]) mapAllCombinationsFigure1.keySet().toArray()[i], 1));
        }

        //минимальной значение функции
        for (int j = 0; j < mapBestFigure2.size(); j++) {
            if (min > ((Double) mapBestFigure2.values().toArray()[j])) {

                min = (Double) mapBestFigure2.values().toArray()[j];
                result = (int[][]) mapBestFigure2.keySet().toArray()[j];

            }
        }

        return new Pair<int[][], Double>(result, min);
    }

}


