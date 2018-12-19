package View;

import Model.Field;
import javafx.util.Pair;

import java.util.*;

public class BestSolver extends AllCombinationsReturn {

    private Map<int[][],Double> mapBestFigure2 = new HashMap<int[][], Double>();

    public Pair<int[][], Double> choseBest(List<Integer> shapeQueue){
        int[][] matr = Field.field;
        Map<int[][],Double> mapBestFigure1 = allPeharbsSituation(shapeQueue, matr, 0);


        for (int i = 0; i < mapBestFigure1.size(); i++){
            mapBestFigure2.putAll(allPeharbsSituation(shapeQueue, (int[][]) mapBestFigure1.keySet().toArray()[i],1));
        }

        Double min=Double.MAX_VALUE;
        int[][] result = new int[0][];

        for (int j=0; j<mapBestFigure2.size(); j++){
            if (min>((Double)mapBestFigure2.values().toArray()[j])){
                min=(Double) mapBestFigure2.values().toArray()[j];
                result=(int[][])mapBestFigure2.keySet().toArray()[j];
            }
        }

        return new Pair<int[][], Double>(result,min);
    }

}


