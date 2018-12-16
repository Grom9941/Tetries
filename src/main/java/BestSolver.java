import java.util.*;

public class BestSolver {

    private final static double weighFillRow=1.0;
    private final static double weighHole=26.8;
    private final static double weighHeight=3.5;//наугад пока нудно тестить
    private final static double weighSlot=15.8;
    private Map<int[][],Double> mapBestFigure2 = new HashMap<int[][], Double>();

    public int[][] choseBest(List<Integer> shapeQueue){
        Field field = new Field();
        int[][] matr = field.returnField();
        Map<int[][],Double> mapBestFigure1 = allPeharbsSituation(shapeQueue, matr, 0);

        for (int i = 0; i < 6; i++){
            mapBestFigure2.putAll(allPeharbsSituation(shapeQueue, (int[][]) mapBestFigure1.keySet().toArray()[i],1));

        }

        return (int[][]) mapBestFigure2.keySet().toArray()[mapBestFigure2.size()-1];
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
            for (int i = 0; i < 10 - figure1Width; i++) {

                List<Integer> list = field.listHeight.subList(i, i+figure1Width);

                int max = 0;

                //место куда встанет фигура(является самым большим difference(difference-верхняя строка фигуры)
                for (int j = 0; j < figure1Width; j++) {
                    int difference = list.get(j) - onFloor.get(j);
                    if (difference > max) {
                        max = difference;
                    }
                }
                int[][] matrWithFigure = field.insert(figure1, figure1Length, figure1Width, max, i, matr);//вставили фигуру

                int numberFillRow = numberFillRow(matrWithFigure);
                int numberHole = numberHole(matrWithFigure);
                int maxHeight = maxHeight(matrWithFigure);
                int numberSlot = numberSlot(matrWithFigure);
                double function = (numberFillRow * weighFillRow) + (numberHole * weighHole) + (maxHeight * weighHeight) + (numberSlot * weighSlot);

                mapBestFigure.put(matrWithFigure,function);
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

    private int numberHole(int[][] field) {//количество клеток заполненных с верху не возможно подлесть
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


