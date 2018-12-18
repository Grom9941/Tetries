import javafx.util.Pair;

import java.util.*;

public class BestSolver {
//пространство между фигурами
    private final static double weighFillRow=-5.8;
    private final static double weighHole=4.2;
    private final static double weighHeight=1.2;
    private final static double weighSlot=1.8;
    private final static double weighNumberNotFill=4.8;
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
       // return result;
    }

    private Map<int[][],Double> allPeharbsSituation(List<Integer> shapeQueue, int[][] matr, int numberfigure) {
        Figure figure = new Figure();
        Field field = new Field();
        Map<int[][],Double> mapBestFigure = new HashMap<int[][], Double>();

        for (int k=1;k<=4;k++) {
            int[][] figure1 = figure.figureRandom(shapeQueue.get(numberfigure), k);

            int figure1Length = figure1.length;
            int figure1Width = figure1[0].length;

            List<Integer> onFloor = figure.figureLength(shapeQueue.get(numberfigure), k);
            // row=figure1.length;/10
            //col=figure1[0].length;/20


            //роняем фигуру в одном положении
            for (int i = 0; i <= 10 - figure1Width; i++) {

                    List<Integer> list = heightList(matr).subList(i,i+figure1Width);
                    for(int o=0;o<list.size();o++){
                        if (list.get(o)!=0) list.set(o,20-list.get(o));
                    }
            //    List<Integer> list = field.listHeight.subList(i, i+figure1Width);

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
//                int[][] matrWithFigure = field.insert(figure1, figure1Length, figure1Width, max, i, matr);
                    int numberFillRow = numberFillRow(matrWithFigure);
                    int numberHole = numberHole(matrWithFigure);
                    int maxHeight = maxHeight(matrWithFigure);
                    int numberSlot = numberSlot(matrWithFigure);
                    int numberNotFill = numberNotFill(matrWithFigure);

                    double function = (numberFillRow * weighFillRow) + (numberHole * weighHole) + (maxHeight * weighHeight) + (numberSlot * weighSlot) + (numberNotFill * weighNumberNotFill);

                    mapBestFigure.put(matrWithFigure, function);
                } else mapBestFigure.put(Field.field, Double.MAX_VALUE);
            }
        }
        return mapBestFigure;
    }

    private int numberFillRow(int[][] field) {//количество заполнившихся рядов
        int numberFill=0;
        int count;
        for (int i=0; i<20; i++) {
            count=0;
            for (int j=0;j<10;j++) {
                if (field[i][j] == 1) {
                    count++;
                }
            }
            if (count==10) numberFill++;
        }
        return numberFill;
    }

    private int numberHole(int[][] field) {//количество клеток заполненных с верху
        int numberHole=0;
        for (int i=1; i<20; i++) {
            for (int j=0;j<10;j++) {
                if (field[i][j] == 0 && field[i-1][j]==1) {
                    numberHole++;
                }
            }
        }
        return numberHole;
    }

    private int  maxHeight(int[][] field) {//максимальная высота от пола после установки фигуры
        int max=0;
        boolean check = true;
        for (int i=0; i<20; i++) {
            for (int j=0;j<10;j++) {
                if (field[i][j] != 0 && check) {
                    max=20-i;check=false;
                }
            }
        }
        return max;
    }

    private int numberSlot(int[][] field) {//количество колодцев
        List<Integer> listHeight1 = heightList(field);
        int numberFill=0;

        if(listHeight1.get(0)<listHeight1.get(1)) numberFill++;
        if(listHeight1.get(8)>listHeight1.get(9)) numberFill++;

        for (int i=1; i<listHeight1.size()-1; i++) {
           if(listHeight1.get(i-1)>listHeight1.get(i) && listHeight1.get(i)<listHeight1.get(i+1)) numberFill++;
        }
        return numberFill;
    }

    private int numberNotFill(int[][] field) {//если ряд не заполнен больше чем 3 клетками
        List<Integer> listHeight1 = heightList(field);
        int numberNotFill=0;
        int cout;
        for (int j=0;j<10;j++) {
            cout=0;
            for (int i=19; i>=0; i--) {
                if (field[i][j] == 0) {
                    cout++;
                } else {
                    if (cout>=3) numberNotFill++;
                    cout=0;
                }
            }
        }

//        if(listHeight1.get(0)<=(listHeight1.get(1)-3)) numberNotFill++;
//        if((listHeight1.get(8)-3)>=listHeight1.get(9)) numberNotFill++;
//
//        for (int i=1; i<listHeight1.size()-1; i++) {
//            if((listHeight1.get(i-1)-3)>=listHeight1.get(i) && listHeight1.get(i)<(listHeight1.get(i+1)-3)) numberNotFill++;
//        }
        return numberNotFill;
    }

    private List<Integer> heightList(int[][] field) {
        List<Integer> listHeight1 = new ArrayList<Integer>(Collections.nCopies(10, 0));
        boolean cellFill;
        int i1 = 0;

        for (int j=0;j<10;j++){
            cellFill=false;

            for (int i=0;i<=19;i++){
                if (field[i][j]!=0) {
                    cellFill=true;
                    i1=i;
                    break;
                }
            }
            if (cellFill) {
                if (listHeight1.get(j) != i1) {
                    listHeight1.set(j, i1);
                }
            }
        }
        return listHeight1;
    }
}


