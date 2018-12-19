package View;

import Model.Field;
import Model.Figure;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCombinationsReturn extends MethodForFuncton {

    private final static double weighFillRow = -5.8;
    private final static double weighHole = 4.2;
    private final static double weighHeight = 1.2;
    private final static double weighSlot = 1.8;
    private final static double weighNumberNotFill1 = 4.8;
    private final static double weighNumberNotFill2 = 2.8;

    protected Map<int[][], Double> allPeharbsSituation(List<Integer> shapeQueue, int[][] matr, int numberfigure) {
        Figure figure = new Figure();
        Field field = new Field();
        Map<int[][], Double> mapBestFigure = new HashMap<int[][], Double>();

        //4 различных поворота фигуры
        for (int k = 1; k <= 4; k++) {
            int[][] figure1 = figure.figureRandom(shapeQueue.get(numberfigure), k);
            int max = 0;
            int figure1Length = figure1.length;
            int figure1Width = figure1[0].length;

            List<Integer> onFloor = figure.figureLength(shapeQueue.get(numberfigure), k);

            //ставим фигуру в одном положении по всей длине поля
            for (int i = 0; i <= 10 - figure1Width; i++) {

                List<Integer> list = MethodForFuncton.heightList(matr).subList(i, i + figure1Width);
                for (int l = 0; l < list.size(); l++) {
                    if (list.get(l) != 0) list.set(l, 20 - list.get(l));
                }

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

                //при переполнении поля значение функции будет добавленно как Double.Max_Value оно не будет использоваться так как вычисляется минимальное из всех функций
                //в дальнейшем будет использоваться для вычисления полного переполнения поля
                if (!overflow) {

                    int numberFillRow = numberFillRow(matrWithFigure);
                    int numberHole = numberHole(matrWithFigure);
                    int maxHeight = maxHeight(matrWithFigure);
                    int numberSlot = numberSlot(matrWithFigure);

                    Pair<Integer,Integer> numberNotFill = numberNotFill(matrWithFigure);
                    int numberNotFill1 = numberNotFill.getKey();
                    int numberNotFill2 = numberNotFill.getValue();
                    double function = (numberFillRow * weighFillRow) + (numberHole * weighHole) + (maxHeight * weighHeight) +
                            (numberSlot * weighSlot) + (numberNotFill1 * weighNumberNotFill1) + ((numberNotFill2 * weighNumberNotFill2));

                    mapBestFigure.put(matrWithFigure, function);

                } else mapBestFigure.put(Field.field, Double.MAX_VALUE);
            }
        }
        return mapBestFigure;
    }
}
