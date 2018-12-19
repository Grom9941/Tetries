package View;

import Model.Field;
import Model.Figure;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCombinationsReturn extends MethodForFuncton {

    private final static double weighFillRow=-5.8;
    private final static double weighHole=4.2;
    private final static double weighHeight=1.2;
    private final static double weighSlot=1.8;
    private final static double weighNumberNotFill=4.8;

    protected Map<int[][],Double> allPeharbsSituation(List<Integer> shapeQueue, int[][] matr, int numberfigure) {
        Figure figure = new Figure();
        Field field = new Field();
        Map<int[][],Double> mapBestFigure = new HashMap<int[][], Double>();

        for (int k=1;k<=4;k++) {
            int[][] figure1 = figure.figureRandom(shapeQueue.get(numberfigure), k);

            int figure1Length = figure1.length;
            int figure1Width = figure1[0].length;

            List<Integer> onFloor = figure.figureLength(shapeQueue.get(numberfigure), k);

            //роняем фигуру в одном положении
            for (int i = 0; i <= 10 - figure1Width; i++) {

                List<Integer> list = MethodForFuncton.heightList(matr).subList(i,i+figure1Width);
                for(int o=0;o<list.size();o++){
                    if (list.get(o)!=0) list.set(o,20-list.get(o));
                }

                int max = 0;
                //место куда встанет фигура(является самым большим difference(difference-верхняя строка фигуры)
                for (int j = 0; j < figure1Width; j++) {
                    int difference = list.get(j) - onFloor.get(j);
                    if (difference > max) {
                        max = difference;
                    }
                }
                Pair fieldWithFigrue = field.insert(figure1, figure1Length, figure1Width, max, i, matr);
                Boolean overflow = (Boolean) fieldWithFigrue.getValue();
                int[][] matrWithFigure = (int[][]) fieldWithFigrue.getKey();
                if (!overflow) {
                    int numberFillRow = MethodForFuncton.numberFillRow(matrWithFigure);
                    int numberHole = MethodForFuncton.numberHole(matrWithFigure);
                    int maxHeight = MethodForFuncton.maxHeight(matrWithFigure);
                    int numberSlot = MethodForFuncton.numberSlot(matrWithFigure);
                    int numberNotFill = MethodForFuncton.numberNotFill(matrWithFigure);

                    double function = (numberFillRow * weighFillRow) + (numberHole * weighHole) + (maxHeight * weighHeight) + (numberSlot * weighSlot) + (numberNotFill * weighNumberNotFill);

                    mapBestFigure.put(matrWithFigure, function);
                } else mapBestFigure.put(Field.field, Double.MAX_VALUE);
            }
        }
        return mapBestFigure;
    }
}